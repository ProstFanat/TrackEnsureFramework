package eldTransactionsTest;

import base.BaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.driver.DriverFactory;
import ui.pageObjects.businessObjects.AdminSidebarBO;
import ui.pageObjects.businessObjects.DriversBO;
import ui.pageObjects.businessObjects.LoginBO;
import ui.pageObjects.businessObjects.MainScreenBO;
import utils.PropertiesReader;

import java.util.Map;

import static com.codeborne.selenide.Selenide.sleep;

public class CommitTransactionTest extends BaseTest {

    @BeforeMethod
    public void setUp(){
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
    }

    @Test
    public void test(){
        new AdminSidebarBO().openEldTransactionPage()
                //.filterTransactionsByDate(5, "September", 2021, 8, "September", 2021)
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver("FirstName83282 LastName83282")
                .filterByUser("Automation Script")
                .verifyTableContainsTransactionsOnlyWithDriver("FirstName83282 LastName83282");
        sleep(5000);
    }

    @Test
    public void commitTransactionTest(){
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage();
        Integer driverId = new DriversBO().createDriver();
        new DriversBO().openHosPageForDriver("FirstName" + driverId, "LastName" + driverId)
                .openHosEditorPage()
                .openTransaction(PropertiesReader.getProperty("DESCRIPTION"))
                .processTransaction(PropertiesReader.getProperty("DESCRIPTION"));
        String driver = String.format("FirstName%s LastName%s", driverId, driverId);
        Selenide.close();
        DriverFactory.initDriver();
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
        new AdminSidebarBO().openEldTransactionPage()
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"))
                .filterByDriver(driver)
                .verifyTableContainsTransactionsOnlyWithDriver(driver)
                .openViewPageForTransaction();
    }
}
