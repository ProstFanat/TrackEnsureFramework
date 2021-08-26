package ui.decorator;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.BasePage;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class DatePicker {
    private static final Logger LOG = Logger.getLogger(DatePicker.class);

    @Step("Select date from '{dayFrom}-{monthFrom}-{yearFrom}' to '{dayTo}-{monthTO}-{yearTo}'")
    public static void selectDateFromAndDateTo(SelenideElement datePicker,
                                               int dayFrom, String monthFrom, int yearFrom, int dayTo, String monthTo,  int yearTo){
        SelenideElement chooseMonthAndYearBtn = $x("//div[@class = 'bs-datepicker-head']//button[@class='current']");
        datePicker.click();
        BasePage.waitForPageLoaded();
        chooseMonthAndYearBtn.click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", yearFrom)).click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", monthFrom)).click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", dayFrom)).click();

        BasePage.waitForPageLoaded();
        chooseMonthAndYearBtn.click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", yearTo)).click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", monthTo)).click();
        $x(String.format("//td[@role='gridcell']//span[contains(text(), '%s')]", dayTo)).click();

        LOG.info(String.format("Select date from '%s-%s-%s' to '%s-%s-%s'", dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo));
    }
}
