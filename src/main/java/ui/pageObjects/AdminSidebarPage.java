package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class AdminSidebarPage {
    private static final Logger LOG = Logger.getLogger(AdminSidebarPage.class);

    private final SelenideElement btnEldTransactions = $x("//a[@uib-tooltip='ELD Transactions']");

    @Step("Click btn eld transactions")
    public AdminSidebarPage clickEldTransactionsBtn(){
        btnEldTransactions.click();
        LOG.info("Click btn eld transactions");
        return this;
    }
}
