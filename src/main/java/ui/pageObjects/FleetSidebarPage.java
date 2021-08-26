package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class FleetSidebarPage {
    private static final Logger LOG = Logger.getLogger(FleetSidebarPage.class);

    SelenideElement assetsBtn = $x("//div[@id = 'company-profile-dropdown']"),
            driversBtn = $x("//div[@id = 'company-profile-dropdown']//a[@is-permitted-for = 'read.DRIVERS']");

    @Step("Click Assets Button")
    public FleetSidebarPage clickAssetsBtn(){
        assetsBtn.click();
        LOG.info("Click Assets Button");
        return this;
    }

    @Step("Click drivers button")
    public FleetSidebarPage clickDriversBtn(){
        driversBtn.click();
        LOG.info("Click drivers button");
        return this;
    }
}
