package eldTransactionsTest;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.driver.DriverFactory;
import ui.pageObjects.businessObjects.*;
import utils.PropertiesReader;

public class TakeTransactionTest extends BaseTest {
    String driver = "";

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
    }

    @Test
    public void takeTransactionFromViewPageTest(){
        new AdminSidebarBO().openEldTransactionPage()
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .openViewPageForTransaction()
                .closeInstructionWindow()
                .closeSupportingInformationWindow()
                .takeTransaction();
        Selenide.refresh();
        new ViewTransactionBO()
                .verifyThatRightUserSetToOwnedBy(PropertiesReader.getProperty("USER_NAME"));
    }

    @Test
    public void takeTransactionFromEldTransactionsPageTest(){
        new AdminSidebarBO().openEldTransactionPage()
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .takeTransaction()
                .verifyThatRightUserSetToOwnedBy(PropertiesReader.getProperty("USER_NAME"));
    }
}
