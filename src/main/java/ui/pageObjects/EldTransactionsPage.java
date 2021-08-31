package ui.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.decorator.DatePicker;
import ui.decorator.ListOfElements;

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
            actionView = $x("//li[@role = 'menuitem']//*[contains(text(), 'View')]"),
            actionTake = $x("//li[@role = 'menuitem']//*[contains(text(), 'Take')]");
    private final ElementsCollection
            listOfEntityInSelect = $$x("//div[@role = 'option']"),
            driversColumns = $$x("//datatable-body//datatable-row-wrapper//datatable-body-cell[6]"),
            ownedByColumns = $$x("//datatable-body//datatable-row-wrapper//datatable-body-cell[8]"),
            rejectComment = $$x("//datatable-body//datatable-row-wrapper//datatable-body-cell[19]");

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

    @Step("Click on action Take")
    public EldTransactionsPage clickTakeAction(){
        actionTake.click();
        LOG.info("Click on action Take");
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

    @Step("Select Status - '{status}' in filter")
    public EldTransactionsPage selectStatusInFilter(String status){
        transactionStatusSelect.clear();
        transactionStatusSelect.setValue(status);
        ListOfElements.clickOnEntityFromList(status, listOfEntityInSelect);
        LOG.info(String.format("Input status - '%s' in filter", status));
        return this;
    }

    @Step("Select organization - '{organization}' in filter")
    public EldTransactionsPage selectOrganizationInFilter(String organization){
        organizationInput.clear();
        organizationInput.setValue(organization);
        ListOfElements.clickOnEntityFromList(organization, listOfEntityInSelect);
        LOG.info(String.format("Input organization - '%s' in filter", organization));
        return this;
    }

    @Step("Select driver - '{driver}' in filter")
    public EldTransactionsPage selectDriverInFilter(String driver){
        driverInput.clear();
        driverInput.setValue(driver);
        ListOfElements.clickOnEntityFromList(driver, listOfEntityInSelect);
        LOG.info(String.format("Input driver - '%s' in filter", driver));
        return this;
    }

    @Step("Select user - '{user}' in filter")
    public EldTransactionsPage selectUserInFilter(String user){
        userInput.clear();
        userInput.setValue(user);
        ListOfElements.clickOnEntityFromList(user, listOfEntityInSelect);
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

    @Step("Is owned by set of right user")
    public boolean isOwnedBySetRightUser(String userName){
        boolean result = ownedByColumns.first().getText().contains(userName);
        LOG.info("Is owned by set of right user - " + result);
        return result;
    }

    @Step("Click on reject comment")
    public EldTransactionsPage clickOnRejectComment(){
        rejectComment.first().click();
        LOG.info("click on reject comment");
        return this;
    }

    @Step("Is rejected comment equals expected")
    public boolean isRejectedCommentContainsExpected(String comment){
        boolean result = rejectComment.first().getText().trim().contains(comment);
        LOG.info(rejectComment.first().getText());
        LOG.info("Is rejected comment contains expected - " + result);
        return result;
    }


}
