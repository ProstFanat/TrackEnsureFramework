package ui.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.decorator.DatePicker;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class EldTransactionsPage {
    private static final Logger LOG = Logger.getLogger(EldTransactionsPage.class);

    private final SelenideElement
            transactionStatusSelect = $x("//ng-select[@placeholder = 'Status']//input"),
            dateSelect = $x("//input[@placeholder = 'Transaction Date']"),
            organizationInput = $x("//ng-select[@placeholder = 'Organization']//input"),
            driverInput = $x("//ng-select[@placeholder = 'Driver']//input"),
            userInput = $x("//ng-select[@placeholder = 'By User']//input"),
            myTransactionsCheckbox = $x("//label[contains(text(), 'My Transactions')]//input"),
            btnSendNotification = $x("//button[contains(text(), 'Send Notification')]"),
            btnFilterEldTransactions = $x("//button[contains(text(), 'Filter ELD Transactions')]"),
            btnActions = $x("//datatable-body//button"),
            actionView = $x("//li[@role = 'menuitem']//*[contains(text(), 'View')]");
    private final ElementsCollection
            listOfEntityInSelect = $$x("//div[@role = 'option']"),
            driversColumns = $$x("//datatable-body//datatable-row-wrapper//datatable-body-cell[6]");

    @Step("Click btn Actions")
    public EldTransactionsPage clickActionsBtn(){
        btnActions.click();
        LOG.info("Click btn Actions");
        return this;
    }

    @Step("Click on action View")
    public EldTransactionsPage clickViewAction(){
        actionView.click();
        LOG.info("Click on action View");
        return this;
    }

    @Step("Click on transaction status select")
    public EldTransactionsPage clickOnTransactionStatusSelect(){
        transactionStatusSelect.click();
        LOG.info("Click on transaction status select");
        return this;
    }

    @Step("Input {status} into transaction status select")
    public EldTransactionsPage inputTransactionStatus(String status){
        transactionStatusSelect.clear();
        transactionStatusSelect.setValue(status);
        LOG.info(String.format("Input %s into transaction status select", status));
        return this;
    }

    @Step("Click on date filter select")
    public EldTransactionsPage clickOnDateSelect(){
        dateSelect.click();
        LOG.info("Click on date filter select");
        return this;
    }

    @Step("Select date from '{dayFrom}-{monthFrom}-{yearFrom}' to '{dayTo}-{monthTO}-{yearTo}'")
    public EldTransactionsPage selectDateInFilter(int dayFrom, String monthFrom, int yearFrom, int dayTo, String monthTo, int yearTo){
        DatePicker.selectDateFromAndDateTo(dateSelect, dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo);
        return this;
    }

    @Step("Click btn filter eld Transactions")
    public EldTransactionsPage clickFilterTransactionsBtn(){
        btnFilterEldTransactions.click();
        LOG.info("Click btn filter eld Transactions");
        return this;
    }

    @Step("Click on entity '{entity}' from list of select")
    public EldTransactionsPage clickOnEntityFromList(String entity){
        boolean isFind = false;
        LOG.info("Looking for desired Entity");
        for (SelenideElement currentEntity: listOfEntityInSelect) {
            if (currentEntity.getText().trim().equals(entity)){
                currentEntity.click();
                LOG.info("Click on the desired entity - " + entity);
                isFind = true;
                break;
            }
        }
        if(!isFind) LOG.info("Desired entity not found");
        return this;
    }

    @Step("Select organization - '{organization}' in filter")
    public EldTransactionsPage selectOrganizationInFilter(String organization){
        organizationInput.clear();
        organizationInput.setValue(organization);
        clickOnEntityFromList(organization);
        LOG.info(String.format("Input organization - '%s' in filter", organization));
        return this;
    }

    @Step("Select driver - '{driver}' in filter")
    public EldTransactionsPage selectDriverInFilter(String driver){
        driverInput.clear();
        driverInput.setValue(driver);
        clickOnEntityFromList(driver);
        LOG.info(String.format("Input driver - '%s' in filter", driver));
        return this;
    }

    @Step("Select user - '{user}' in filter")
    public EldTransactionsPage selectUserInFilter(String user){
        userInput.clear();
        userInput.setValue(user);
        clickOnEntityFromList(user);
        LOG.info(String.format("Input driver - '%s' in filter", user));
        return this;
    }

    @Step("is table contains transactions only with driver - {driver}")
    public Boolean isTableContainsTransactionsOnlyWithDriver(String driver){
        LOG.info("Checking table on driver");
        for (SelenideElement driverColumn: driversColumns) {
            if (!driverColumn.getText().trim().equals(driver)){
                LOG.info("Error! Find transaction with another driver");
                return false;
            }
        }
        return true;
    }
}
