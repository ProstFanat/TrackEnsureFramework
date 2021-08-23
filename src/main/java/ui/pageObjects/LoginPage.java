package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private static final Logger LOG = Logger.getLogger(LoginPage.class);

    private final SelenideElement loginField = $x("//*[@id = 'email']"),
            loginBtn = $x("//input[@value='Log In']"),
            passwordField = $x("//*[@id = 'password']");

    @Step("Click Login btn")
    public MainScreenPage clickLoginBtn(){
        loginBtn.click();
        LOG.info("Click Login button");
        return new MainScreenPage();
    }

    @Step("Input Login Name {loginName}")
    public LoginPage inputLoginName(String loginName){
        loginField.clear();
        loginField.setValue(loginName);
        LOG.info(String.format("Input login name %s", loginName));
        return this;
    }

    @Step("Input password {password}")
    public LoginPage inputPassword(String password){
        passwordField.clear();
        passwordField.setValue(password);
        LOG.info(String.format("Input password %s", password));
        return this;
    }

    @Step("Open Login Page")
    public LoginPage openLoginPage(){
        open(PropertiesReader.getProperty("PAGE_LOGIN"));
        LOG.info(String.format("Open - %s", PropertiesReader.getProperty("PAGE_LOGIN")));
        return this;
    }
}
