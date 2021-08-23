package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class CustomersPage {
    private static final Logger LOG = Logger.getLogger(CustomersPage.class);

    SelenideElement orgInput = $x("//input[@placeholder='Organization']"),
            btnSearch = $x("//input[@placeholder='Organization']//..//..//..//button"),
            btnLogAsOrg = $x("//*[contains(@class, 'fa-sign-in')]");

    @Step("Input organization name '{orgName}'")
    public CustomersPage inputOrgName(String orgName){
        orgInput.clear();
        orgInput.setValue(orgName);
        LOG.info(String.format("Input organization name '%s'", orgName));
        return this;
    }

    @Step("Click Search button")
    public CustomersPage clickSearchBtn(){
        btnSearch.click();
        LOG.info("Click Search button");
        return this;
    }

    @Step("Click btn Login as Organization")
    public CustomersPage clickLogAsOrgBtn(){
        btnLogAsOrg.click();
        return this;
    }

}
