package eldTransactionsTest;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import constants.TransactionStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.driver.DriverFactory;
import ui.pageObjects.businessObjects.*;
import utils.PropertiesReader;

public class RejectTransactionTest extends BaseTest {

    @BeforeMethod
    public void setUp(){
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
    }

    @Test
    public void takeTransactionFromViewPageTest(){
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage();
        new DriversBO().openHosPage()
                .openHosEditorPage();
        String driver = new HosEditorBO().createAndProcessedTransactionWithReturningDriverName(PropertiesReader.getProperty("DESCRIPTION"));
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
                .closeSupportingInformationWindow()
                .rejectTransaction(PropertiesReader.getProperty("REJECT_ISSUE_TYPE"),
                        PropertiesReader.getProperty("REJECT_SOLUTION"),
                        PropertiesReader.getProperty("REJECT_ADD_INFO"),
                        PropertiesReader.getProperty("REJECT_SCREENSHOT_URL"))
                .filterByStatus(String.valueOf(TransactionStatus.valueOf("Rejected")))
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .verifyThatRejectCommentEqualsToExpected(PropertiesReader.getProperty("REJECT_COMMENT"));
    }
}
