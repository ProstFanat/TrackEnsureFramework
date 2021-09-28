package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.testng.Assert;
import ui.pageObjects.BasePage;
import ui.pageObjects.ViewTransactionPage;
import utils.PropertiesReader;

import static com.codeborne.selenide.WebDriverRunner.url;

public class ViewTransactionBO {

    private static final Logger LOG = Logger.getLogger(ViewTransactionBO.class);
    private final ViewTransactionPage viewTransactionPage;

    public ViewTransactionBO(){
        viewTransactionPage = new ViewTransactionPage();
    }

    @Step("Take transaction")
    public ViewTransactionBO takeTransaction(){
        BasePage.waitForPageLoaded();
        viewTransactionPage.clickTakeTransactionBtn();
        LOG.info("Transaction was taken");
        return this;
    }

    @Step("Close Instruction window")
    public ViewTransactionBO closeInstructionWindow(){
        viewTransactionPage.clickOkBtn();
        LOG.info("Instruction window was close");
        return this;
    }

    @Step("Close Supporting information window")
    public ViewTransactionBO closeSupportingInformationWindow(){
        viewTransactionPage.clickCloseBtn();
        LOG.info("Supporting information window was close");
        return this;
    }

    @Step("Commit transaction")
    public EldTransactionsBO commitTransaction(){
        viewTransactionPage.clickCommitTransactionBtn();
        closeSupportingInformationWindow();
        viewTransactionPage.inputComment(PropertiesReader.getProperty("DESCRIPTION"))
                .clickBtnCommitTransactionInModalWindow();
        BasePage.waitForPageLoaded();
        LOG.info("Transaction committed");
        return new EldTransactionsBO();
    }

    @Step("Reject transaction")
    public EldTransactionsBO rejectTransaction(String issue, String solution, String addInfo, String url){
        BasePage.waitForPageLoaded();
        viewTransactionPage.clickRejectBtn()
                .inputIssueType(issue)
                .inputSolution(solution)
                .inputAdditionalInfoToRejectSolution(addInfo)
                .inputScreenshotUrl(url);
        verifyThatPreviewMessageContainsMessage(PropertiesReader.getProperty("REJECT_COMMENT"));
        viewTransactionPage.clickBtnRejectInModalWindow();
        BasePage.waitForPageLoaded();
        LOG.info("Transaction rejected");
        return new EldTransactionsBO();
    }

    @Step("Open reject modal window")
    public ViewTransactionBO openRejectModalWindow(){
        BasePage.waitForPageLoaded();
        viewTransactionPage.clickRejectBtn();
        LOG.info("Open transaction modal window");
        return this;
    }

    @Step("Reject transaction")
    public EldTransactionsBO rejectTransaction(){
        viewTransactionPage.clickBtnRejectInModalWindow();
        BasePage.waitForPageLoaded();
        LOG.info("Transaction rejected");
        return new EldTransactionsBO();
    }

    @Step("select issue type")
    public ViewTransactionBO selectIssueType(String issue){
        viewTransactionPage.inputIssueType(issue);
        LOG.info("Select issue type - " + issue);
        return this;
    }

    @Step("Input solution")
    public ViewTransactionBO inputSolution(String solution){
        viewTransactionPage.inputSolution(solution);
        LOG.info("Input solution - " + solution);
        return this;
    }

    @Step("Input screenshot url")
    public ViewTransactionBO inputScreenshotUrl(String url){
        viewTransactionPage.inputScreenshotUrl(url);
        LOG.info("Input screenshot url - " + url);
        return this;
    }

    @Step("Input comment")
    public ViewTransactionBO inputComment(String comment){
        viewTransactionPage.inputComment(comment);
        LOG.info("Input comment - " + comment);
        return this;
    }

    @Step("Cancel reject")
    public ViewTransactionBO cancelReject(){
        viewTransactionPage.clickBtnCancelInModalWindow();
        LOG.info("Cancel reject");
        return this;
    }

    @Step("Get transaction id")
    public Integer getTransactionId(){
        int indexOfTransactionId = url().indexOf("transactionId");
        String tempString = url().substring(indexOfTransactionId);
        int result = Integer.parseInt(tempString.substring(14, 21));
        LOG.info("Get transaction id - " + result);
        return result;
    }

    @Step("Set checkbox repeated mistake {value}")
    public ViewTransactionBO setRepeatedMistakeCheckbox(boolean value){
        viewTransactionPage.setCheckboxRepeatedMistakeProcess(value);
        LOG.info("Checkbox set " + value);
        return this;
    }

    @Step("Verify that correct preview message displayed")
    public ViewTransactionBO verifyThatPreviewMessageContainsMessage(String message){
        viewTransactionPage.clickBtnPreviewInModalWindow();
        Assert.assertTrue(viewTransactionPage.getPreviewRejectComment().contains(message), "reject message is not equals to expected");
        return this;
    }

    @Step("Verify that owned by set right user")
    public ViewTransactionBO verifyThatRightUserSetToOwnedBy(String user){
        Assert.assertTrue(viewTransactionPage.isOwnedBySetRightUser(user), "To Owned by set wrong user");
        return this;
    }

    @Step("Verify that Reject btn in modal window displayed")
    public ViewTransactionBO verifyThatRejectBtnInModalWindowDisplayed(){
        Assert.assertTrue(viewTransactionPage.isRejectButtonInModalWindowDisplayed());
        return this;
    }
}
