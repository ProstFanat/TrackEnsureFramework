package ui.driver;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import utils.PropertiesReader;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DriverFactory {
    private static final Logger LOG = Logger.getLogger(DriverFactory.class);

    @Step("Configuration driver with default parameters")
    public static void initDriver(){
        Configuration.timeout = 10000;
        LOG.info("Set Implicitly wait 10 second");
        Configuration.startMaximized = true;
        LOG.info("Driver set maximize");
        open(PropertiesReader.getProperty("HOST"));
        LOG.info("Driver started");
    }
}
