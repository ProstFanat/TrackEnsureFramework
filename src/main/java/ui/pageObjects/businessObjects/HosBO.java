package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.HosPage;

public class HosBO {
    private static final Logger LOG = Logger.getLogger(MainScreenBO.class);
    private final HosPage hosPage;

    public HosBO(){
        hosPage = new HosPage();
    }

    @Step("Open HOS Editor page")
    public HosEditorBO openHosEditorPage(){
        hosPage.clickHosReportTab()
                .clickGenerateReport()
                .clickGoToEditorPage();
        LOG.info("Open hos Editor page");
        return new HosEditorBO();
    }
}
