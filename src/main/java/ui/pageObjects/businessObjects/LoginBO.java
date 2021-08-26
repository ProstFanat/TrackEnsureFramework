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
    public MainScreenBO loginToTrackEnsure(String loginName, String password){
        loginPage.openLoginPage()
                .inputLoginName(loginName)
                .inputPassword(password)
                .clickLoginBtn()
                .clickCancelShiftBtn();
        LOG.info(String.format("Login user with login '%s' and password '%s'",
                PropertiesReader.getProperty("LOGIN_NAME"),
                PropertiesReader.getProperty("LOGIN_PASS")));
        return new MainScreenBO();
    }


}
