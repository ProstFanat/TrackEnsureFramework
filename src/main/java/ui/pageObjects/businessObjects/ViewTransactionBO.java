package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.testng.Assert;
import ui.pageObjects.BasePage;
import ui.pageObjects.ViewTransactionPage;
import utils.PropertiesReader;

public class ViewTransactionBO {

    private static final Logger LOG = Logger.getLogger(ViewTransactionBO.class);
    private final ViewTransactionPage viewTransactionPage;

    public ViewTransactionBO(){
        viewTransactionPage = new ViewTransactionPage();
    }

    @Step("Take transaction")
    public ViewTransactionBO takeTransaction(){
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
        verifyThatCorrectPreviewMessageDisplayed(PropertiesReader.getProperty("REJECT_COMMENT"));
        viewTransactionPage.clickBtnRejectInModalWindow();
        BasePage.waitForPageLoaded();
        LOG.info("Transaction rejected");
        return new EldTransactionsBO();
    }

//    @Step("Get preview reject message")
//    public ViewTransactionBO getRejectMessage(){
//        viewTransactionPage.clickBtnPreviewInModalWindow()
//                .getPreviewRejectComment();
//    }

    @Step("Verify that correct preview message displayed")
    public ViewTransactionBO verifyThatCorrectPreviewMessageDisplayed(String message){
        viewTransactionPage.clickBtnPreviewInModalWindow();
        Assert.assertTrue(viewTransactionPage.getPreviewRejectComment().equals(message), "reject message is not equals to expected");
        return this;
    }

    @Step("Verify that owned by set right user")
    public ViewTransactionBO verifyThatRightUserSetToOwnedBy(String user){
        Assert.assertTrue(viewTransactionPage.isOwnedBySetRightUser(user), "To Owned by set wrong user");
        return this;
    }


}
