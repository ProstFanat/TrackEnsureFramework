package eldTransactionsTest;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import constants.TransactionStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.dataProvider.ScreenshotUrls;
import ui.driver.DriverFactory;
import ui.pageObjects.EldTransactionsPage;
import ui.pageObjects.businessObjects.*;
import utils.PropertiesReader;

public class RejectTransactionTest extends BaseTest {
    private String driver = "";

    @BeforeMethod
    public void setUp(){
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage();
        new DriversBO().openHosPage()
                .openHosEditorPage();
        driver = new HosEditorBO().createAndProcessedTransactionWithReturningDriverName(PropertiesReader.getProperty("DESCRIPTION"));
        Selenide.close();
        DriverFactory.initDriver();
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
        new AdminSidebarBO().openEldTransactionPage()
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .openViewPageForTransaction()
                .closeInstructionWindow()
                .closeSupportingInformationWindow()
                .takeTransaction();
        Selenide.refresh();
        new ViewTransactionBO().verifyThatRightUserSetToOwnedBy(PropertiesReader.getProperty("USER_NAME"))
                .closeInstructionWindow()
                .closeSupportingInformationWindow();
    }

    @Test
    public void rejectTransactionWithIssueAndSolutionTest(){
        new ViewTransactionBO().rejectTransaction(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"),
                PropertiesReader.getProperty("REJECT_SOLUTION"),
                PropertiesReader.getProperty("REJECT_ADD_INFO"),
                PropertiesReader.getProperty("REJECT_SCREENSHOT_URL"))
        .filterByStatus(String.valueOf(TransactionStatus.valueOf("Rejected")))
        .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
        .filterByDriver(driver)
        .verifyTableContainsTransactionsOnlyWithDriver(driver)
        .openRejectComment()
        .verifyThatRejectCommentContainsExpected(PropertiesReader.getProperty("REJECT_COMMENT"))
        .verifyThatRejectCommentContainsExpected(PropertiesReader.getProperty("REJECT_SCREENSHOT_URL"));
    }

    @Test
    public void rejectTransactionWithoutIssueType(){
        new ViewTransactionBO().openRejectModalWindow()
                .rejectTransaction();
        new ViewTransactionBO().verifyThatRejectBtnInModalWindowDisplayed();
    }

    @Test
    public void rejectTransactionWithoutSolution(){
        new ViewTransactionBO().openRejectModalWindow()
                .selectIssueType(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"))
                .verifyThatRejectBtnInModalWindowDisplayed();
    }

    @Test
    public void rejectTransactionWithoutAddInfo(){
        new ViewTransactionBO().openRejectModalWindow()
                .selectIssueType(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"))
                .inputSolution(PropertiesReader.getProperty("REJECT_SOLUTION"))
                .verifyThatRejectBtnInModalWindowDisplayed();
    }

    @Test(dataProvider = "valid-urls", dataProviderClass = ScreenshotUrls.class)
    public void rejectTransactionWithValidUrls(String url){
        new ViewTransactionBO().rejectTransaction(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"),
                PropertiesReader.getProperty("REJECT_SOLUTION"),
                PropertiesReader.getProperty("REJECT_ADD_INFO"),
                url)
        .filterByStatus(String.valueOf(TransactionStatus.valueOf("Rejected")))
        .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
        .filterByDriver(driver)
        .verifyTableContainsTransactionsOnlyWithDriver(driver)
        .openRejectComment()
        .verifyThatRejectCommentContainsExpected(PropertiesReader.getProperty("REJECT_COMMENT"))
        .verifyThatRejectCommentContainsExpected(url);
    }

    @Test(dataProvider = "invalid-urls", dataProviderClass = ScreenshotUrls.class)
    public void rejectTransactionWithInValidUrls(String url){
        new ViewTransactionBO().rejectTransaction(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"),
                PropertiesReader.getProperty("REJECT_SOLUTION"),
                PropertiesReader.getProperty("REJECT_ADD_INFO"),
                url)
                .filterByStatus(String.valueOf(TransactionStatus.valueOf("Rejected")))
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .openRejectComment()
                .verifyThatRejectCommentContainsExpected(PropertiesReader.getProperty("REJECT_COMMENT"))
                .verifyThatRejectCommentNotContainsExpected(url);
    }

    @Test
    public void rejectTransactionWithTypeOtherValidTest(){
        new ViewTransactionBO().openRejectModalWindow()
                .selectIssueType(PropertiesReader.getProperty("REJECT_ISSUE_OTHER"))
                .inputComment(PropertiesReader.getProperty("REJECT_COMMENT"))
                .rejectTransaction()
                .filterByStatus(String.valueOf(TransactionStatus.valueOf("Rejected")))
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .openRejectComment()
                .verifyThatRejectCommentContainsExpected(PropertiesReader.getProperty("REJECT_COMMENT"));
    }

    @Test
    public void rejectTransactionWithTypeOtherInValidTest(){
        new ViewTransactionBO().openRejectModalWindow()
                .selectIssueType(PropertiesReader.getProperty("REJECT_ISSUE_OTHER"))
                .rejectTransaction();
        new ViewTransactionBO().verifyThatRejectBtnInModalWindowDisplayed();
    }

    @Test
    public void rejectTransactionCancelReject(){
        new ViewTransactionBO().openRejectModalWindow()
                .cancelReject();
        Selenide.back();
        new EldTransactionsBO().filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver);
    }

    @Test
    public void rejectWithRepeatedMistakeProcess(){
        int transactionId = new ViewTransactionBO().getTransactionId();
    }

}
