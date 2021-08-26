import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.pageObjects.businessObjects.AdminSidebarBO;
import ui.pageObjects.businessObjects.LoginBO;
import ui.pageObjects.businessObjects.MainScreenBO;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.sleep;

public class TestPage extends BaseTest{

    @BeforeMethod
    public void setUp(){
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS"))
                .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
    }

    @Test
    public void testTransactions(){
        new AdminSidebarBO().openEldTransactionPage()
                .filterTransactionsByDate(5, "September", 2021, 8, "September", 2021)
                .filterByOrganization(PropertiesReader.getProperty("ORG_NAME"));
        sleep(5000);
    }

    @Test
    public void testCreateTransaction(){
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage()
                .openHosPageForDriver("Test", "Company")
                .openHosEditorPage()
                .createAndProcessedTransactionWithReturningDriverName();
    }

    @Test
    public void testCreateDriver(){
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage()
                .createDriverDefaultParameters();
    }



}
