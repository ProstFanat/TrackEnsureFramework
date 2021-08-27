package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.AdminSidebarPage;
import ui.pageObjects.BasePage;
import ui.pageObjects.EldTransactionsPage;

public class AdminSidebarBO {
    private static final Logger LOG = Logger.getLogger(AdminSidebarBO.class);
    private final AdminSidebarPage adminSidebarPage;

    public AdminSidebarBO(){
        adminSidebarPage = new AdminSidebarPage();
    }

    @Step("Open ELD Transactions page")
    public EldTransactionsBO openEldTransactionPage(){
        adminSidebarPage.clickEldTransactionsBtn();
        BasePage.waitForPageLoaded();
        LOG.info("Open ELD Transactions page");
        return new EldTransactionsBO();
    }
}
