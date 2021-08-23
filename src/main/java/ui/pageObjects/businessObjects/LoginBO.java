package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.LoginPage;
import utils.PropertiesReader;

public class LoginBO {
    private static final Logger LOG = Logger.getLogger(LoginBO.class);
    private final LoginPage loginPage;

    public LoginBO(){
        loginPage = new LoginPage();
    }

    @Step("Log in to TrackEnsure")
    public MainScreenBO loginToTrackEnsure(){
        loginPage.openLoginPage()
                .inputLoginName(PropertiesReader.getProperty("LOGIN_NAME"))
                .inputPassword(PropertiesReader.getProperty("LOGIN_PASS"))
                .clickLoginBtn()
                .clickCancelShiftBtn();
        return new MainScreenBO();
    }


}
