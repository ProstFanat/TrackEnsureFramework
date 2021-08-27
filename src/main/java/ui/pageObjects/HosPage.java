package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class HosPage {
    private static final Logger LOG = Logger.getLogger(HosPage.class);

    private final SelenideElement hosReportTab = $x("//*[@is-permitted-for = 'read.ELD-VIEWER']"),
            btnGenerateReport = $x("//*[@ng-click='vm.goToHosReport()']"),
            btnGoToEditor = $x("//*[@ispermittedfor='read.ELD-EDITOR']");


    @Step("Click hos report tab")
    public HosPage clickHosReportTab(){
        hosReportTab.click();
        LOG.info("Click hos report tab");
        return this;
    }

    @Step("Click btn generate report")
    public HosPage clickGenerateReport(){
        btnGenerateReport.click();
        LOG.info("Click btn generate report");
        return this;
    }

    @Step("Click btn go to editor")
    public HosPage clickGoToEditorPage(){
        btnGoToEditor.click();
        LOG.info("Click btn go to Editor Page");
        return this;
    }
}
