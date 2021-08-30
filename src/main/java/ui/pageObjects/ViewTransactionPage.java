package ui.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.decorator.ListOfElements;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ViewTransactionPage {
    private static final Logger LOG = Logger.getLogger(ViewTransactionPage.class);

    private final SelenideElement
        btnOK = $x("//button[text() = 'ОК']"),
        btnClose = $x("//button[text() = 'Close']"),
        btnTakeTransaction = $x("//button[contains(text(), 'Take Transaction')]"),
        btnRejectTransaction = $x("//button[contains(text(), 'Reject')]"),
        btnCommitTransaction = $x("//button[contains(text(), 'Commit')]"),
        commentInput = $x("//textarea[@placeholder = 'Comment']"),
        btnCommitTransactionModalWindow = $x("//button[contains(text(), 'Commit') and contains(@class, 'btn-success')]"),
        issueTypeInput = $x("//ng-select[@id = 'issueType']//input"),
        solutionInput = $x("//ng-select[@placeholder = 'Solution']//input"),
        btnAddScreenshot = $x("//div[@class = 'tab-content']//button//i[@class = 'fa fa-plus']"),
        repeatedMistakeProcessCheckbox = $x("//*[contains(text(), 'Repeated Mistake')]//..//input"),
        btnRemoveIssue = $x("//button[contains(text(), 'Remove Issue')]"),
        btnAddNewIssue = $x("//button[contains(@class, 'add-issue-btn')]//i[@class = 'fa fa-plus']"),
        btnRejectInModalWindow = $x("//div[@class = 'modal-footer']//button[text() = 'Reject']"),
        btnCancel = $x("//div[@class = 'modal-footer']//button[text() = 'Cancel']"),
        btnPreview = $x("//div[@class = 'modal-footer']//button[text() = 'Preview']"),
        additionalInfoInput = $x("//input[@class = 'form-control form-control-sm ng-pristine ng-valid ng-touched']"),
        previewRejectMessage = $x("//div[@class = 'modal-body']//div[@class = 'col' and @ng-reflect-ng-switch = 'true']");
    private final ElementsCollection
            listOfEntityInSelect = $$x("//div[@role = 'option']"),
            screenshotUrlInputs = $$x("//input[@placeholder = 'Screenshot Url']");

    @Step("Click btn OK")
    public ViewTransactionPage clickOkBtn(){
        btnOK.click();
        LOG.info("Click btn OK");
        return this;
    }

    @Step("Click btn Close")
    public ViewTransactionPage clickCloseBtn(){
        btnClose.click();
        LOG.info("Click btn Close");
        return this;
    }

    @Step("Click btn Take transaction")
    public ViewTransactionPage clickTakeTransactionBtn(){
        btnTakeTransaction.click();
        LOG.info("Click btn Take transaction");
        return this;
    }

    @Step("Click btn Commit Transaction")
    public ViewTransactionPage clickCommitTransactionBtn(){
        btnCommitTransaction.click();
        LOG.info("Click btn Commit transaction");
        return this;
    }

    @Step("Input comment")
    public ViewTransactionPage inputComment(String comment){
        commentInput.clear();
        commentInput.setValue(comment);
        LOG.info("Input comment - " + comment);
        return this;
    }

    @Step("Click btn commit transaction at the modal window")
    public ViewTransactionPage clickBtnCommitTransactionInModalWindow(){
        btnCommitTransactionModalWindow.click();
        LOG.info("Click btn commit transaction at the modal window");
        return this;
    }

    @Step("Click reject button")
    public ViewTransactionPage clickRejectBtn(){
        btnRejectTransaction.click();
        LOG.info("Click btn Reject transaction");
        return this;
    }

    @Step("Input issue type")
    public ViewTransactionPage inputIssueType(String type){
        issueTypeInput.clear();
        issueTypeInput.setValue(type);
        ListOfElements.clickOnEntityFromList(type, listOfEntityInSelect);
        LOG.info("Input issue type - " + type);
        return this;
    }

    @Step("Input solution")
    public ViewTransactionPage inputSolution(String solution){
        solutionInput.clear();
        solutionInput.setValue(solution);
        ListOfElements.clickOnEntityFromList(solution, listOfEntityInSelect);
        LOG.info("Input solution - " + solution);
        return this;
    }

    @Step("Input screenshot url")
    public ViewTransactionPage inputScreenshotUrl(String url){
        screenshotUrlInputs.first().clear();
        screenshotUrlInputs.first().setValue(url);
        LOG.info("Input screenshot url - " + url);
        return this;
    }

    @Step("Click btn add new screenshot url")
    public ViewTransactionPage clickAddNewScreenshotUrl(){
        btnAddScreenshot.click();
        LOG.info("Click btn add new screenshot url");
        return this;
    }

    @Step("Set checkbox repeated mistake process")
    public ViewTransactionPage setCheckboxRepeatedMistakeProcess(Boolean value){
        repeatedMistakeProcessCheckbox.setSelected(value);
        LOG.info("Set checkbox repeated mistake process - " + value);
        return this;
    }

    @Step("Click btn remove issue")
    public ViewTransactionPage clickBtnRemoveIssue(){
        btnRemoveIssue.click();
        LOG.info("Click btn remove issue");
        return this;
    }

    @Step("Click btn add new issue")
    public ViewTransactionPage clickAddNewIssueType(){
        btnAddNewIssue.click();
        LOG.info("Click btn add new issue");
        return this;
    }

    @Step("Click btn reject in modal window")
    public ViewTransactionPage clickBtnRejectInModalWindow(){
        btnRejectInModalWindow.click();
        LOG.info("click reject btn in modal window");
        return this;
    }

    @Step("Click btn Cancel in modal window")
    public ViewTransactionPage clickBtnCancelInModalWindow(){
        btnCancel.click();
        LOG.info("click Cancel btn in modal window");
        return this;
    }

    @Step("Click btn Preview in modal window")
    public ViewTransactionPage clickBtnPreviewInModalWindow(){
        btnPreview.click();
        LOG.info("click Preview btn in modal window");
        return this;
    }

    @Step("Input additional info to reject issue")
    public ViewTransactionPage inputAdditionalInfoToRejectSolution(String info){
        additionalInfoInput.clear();
        additionalInfoInput.setValue(info);
        LOG.info("Input additional info to reject issue - " + info);
        return this;
    }

    @Step("Get preview reject comment")
    public String getPreviewRejectComment(){
        String result = previewRejectMessage.getText();
        LOG.info("Get preview reject comment - " + result);
        return result;
    }

    @Step("Is owned by set right user")
    public boolean isOwnedBySetRightUser(String userName){
        boolean result = $x(String.format("//div[@class = 'content']//*[contains(text(), '%s')]", userName))
                .getText().contains(userName);
        LOG.info("Is owned by set of right user - " + result);
        return result;
    }

    @Step("Is Reject button displayed")
    public boolean isRejectBtnDisplayed(){
        boolean result = btnRejectTransaction.isDisplayed();
        LOG.info("Is reject button displayed? - " + result);
        return result;
    }

    @Step("Is Commit button displayed")
    public boolean isCommitBtnDisplayed(){
        boolean result = btnCommitTransaction.isDisplayed();
        LOG.info("Is Commit button displayed? - " + result);
        return result;
    }

}
