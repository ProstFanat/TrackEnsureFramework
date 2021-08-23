package ui.pageObjects.businessObjects;

import org.apache.log4j.Logger;
import ui.pageObjects.LoginPage;
import utils.PropertiesReader;

public class LoginBO {
    private static final Logger LOG = Logger.getLogger(LoginBO.class);
    private final LoginPage loginPage;

    public LoginBO(){
        loginPage = new LoginPage();
    }

    public MainScreenBO loginToTrackEnsure(){
        loginPage.openLoginPage()
                .inputLoginName(PropertiesReader.getProperty("LOGIN_NAME"))
                .inputPassword(PropertiesReader.getProperty("LOGIN_PASS"))
                .clickLoginBtn()
                .clickCancelShiftBtn();
        return new MainScreenBO();
    }


}
