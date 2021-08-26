import org.testng.annotations.Test;
import ui.pageObjects.businessObjects.HosEditorBO;
import ui.pageObjects.businessObjects.LoginBO;
import ui.pageObjects.businessObjects.MainScreenBO;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.sleep;

public class TestPage extends BaseTest{

    @Test
    public void test(){
        new LoginBO().loginToTrackEnsure(PropertiesReader.getProperty("LOGIN_NAME"),
                                         PropertiesReader.getProperty("LOGIN_PASS"))
                    .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
        new MainScreenBO().openCustomersPage()
                .loginAsOrg(PropertiesReader.getProperty("ORG_NAME"))
                .openDriversPage()
                //.createDriverDefaultParameters()
                .openHosPageForDriver("Test", "Company")
                .openHosEditorPage();
        System.out.println(new HosEditorBO().createAndProcessedTransactionWithReturningUrlAndDriverName("Test").toString());


        sleep(5000);
    }
}
