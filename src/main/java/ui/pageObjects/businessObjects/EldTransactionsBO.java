package ui.pageObjects.businessObjects;

import DB.entity.DAO.EldTransactionRejectCommentDAO;
import DB.entity.EldTransactionRejectComment;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.Assert;
import ui.pageObjects.BasePage;
import ui.pageObjects.EldTransactionsPage;

import static DB.DBConstant.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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
        LOG.info(String.format("Table filtered by date from '%s-%s-%s' to '%s-%s-%s'", dayFrom, monthFrom, yearFrom, dayTo, monthTo, yearTo));
        return this;
    }

    @Step("Filter by user - {user}")
    public EldTransactionsBO filterByStatus(String status){
        eldTransactionsPage.selectStatusInFilter(status)
                .clickFilterTransactionsBtn();
        LOG.info("Table filtered by status - " + status);
        return this;
    }

    @Step("Filter by organization - {org}")
    public EldTransactionsBO filterByOrganization(String org){
        BasePage.waitForPageLoaded();
        eldTransactionsPage.selectOrganizationInFilter(org)
                .clickFilterTransactionsBtn();
        LOG.info("Table filtered by organization - " + org);
        return this;
    }

    @Step("Filter by driver - {driver}")
    public EldTransactionsBO filterByDriver(String driver){
        eldTransactionsPage.selectDriverInFilter(driver)
                .clickFilterTransactionsBtn();
        LOG.info("Table filtered by driver - " + driver);
        return this;
    }

    @Step("Filter by user - {user}")
    public EldTransactionsBO filterByUser(String user){
        eldTransactionsPage.selectUserInFilter(user)
                .clickFilterTransactionsBtn();
        LOG.info("Table filtered by user - " + user);
        return this;
    }

    @Step("Verify that table contains transactions only with driver - {driver}")
    public EldTransactionsBO verifyTableContainsTransactionsOnlyWithDriver(String driver){
        BasePage.waitForPageLoaded();
        boolean isContains = eldTransactionsPage.isTableContainsTransactionsOnlyWithDriver(driver);
        Assert.assertTrue(isContains, "Table Contains transactions with another driver");
        LOG.info("verify Table Contains Transactions Only With Driver - " + driver + " = " + isContains);
        return this;
    }

    @Step("Open view page for transaction")
    public ViewTransactionBO openViewPageForTransaction(){
        eldTransactionsPage.clickActionsBtn()
                .clickViewAction();
        BasePage.waitForPageLoaded();
        LOG.info("View Transaction Page opened");
        return new ViewTransactionBO();
    }

    @Step("Take transaction")
    public EldTransactionsBO takeTransaction(){
        eldTransactionsPage.clickActionsBtn()
                .clickTakeAction();
        BasePage.waitForPageLoaded();
        LOG.info("Transaction was taken");
        return this;
    }

    @Step("Open reject comment")
    public EldTransactionsBO openRejectComment(){
        eldTransactionsPage.clickOnRejectComment();
        LOG.info("Reject comment opened");
        return this;
    }

    @Step("Verify that owned by set right user")
    public EldTransactionsBO verifyThatRightUserSetToOwnedBy(String user){
        Assert.assertTrue(eldTransactionsPage.isOwnedBySetRightUser(user), "To Owned by set wrong user");
        return this;
    }

    @Step("Verify that reject comment contains the {comment}")
    public EldTransactionsBO verifyThatRejectCommentContainsExpected(String comment){
        Assert.assertTrue(eldTransactionsPage.isRejectedCommentContainsExpected(comment), "Reject comment is not contains expected");
        return this;
    }

    @Step("Verify that reject comment NOT contains {comment}")
    public EldTransactionsBO verifyThatRejectCommentNotContainsExpected(String comment){
        Assert.assertFalse(eldTransactionsPage.isRejectedCommentContainsExpected(comment), "Reject comment is contains expected");
        return this;
    }

    @Step("Verify that repeated mistake checkbox is 'Y' in DB eld_transaction_reject_comment")
    public EldTransactionsBO verifyThatRepeatedMistakeCheckboxIsYInDb(int transactionId){
        Assert.assertTrue(new EldTransactionRejectCommentDAO(DB_URL, USER_DB, PASS_DB)
                .isRepeatedIssueByTransactionId(transactionId), "transaction in eld_transaction_reject_comment has incorrect value of is_repeated_mistake");
        return this;
    }
}

