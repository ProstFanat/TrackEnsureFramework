package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.EldTransactionsPage;

public class EldTransactionsBO {
    private static final Logger LOG = Logger.getLogger(MainScreenBO.class);
    private final EldTransactionsPage eldTransactionsPage;

    public EldTransactionsBO(){
        eldTransactionsPage = new EldTransactionsPage();
    }

    @Step("Filter date from '{dayFrom}-{monthFrom}-{yearFrom}' to '{dayTo}-{monthTO}-{yearTo}'")
    public EldTransactionsBO filterTransactionsByDate(int dayFrom, String monthFrom, int yearFrom, int dayTo, String monthTo, int yearTo){
        eldTransactionsPage
                .selectDateInFilter(dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo)
                .clickFilterTransactionsBtn();
        return this;
    }
}
