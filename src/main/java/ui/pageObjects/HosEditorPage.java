package ui.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HosEditorPage {
    private static final Logger LOG = Logger.getLogger(HosEditorPage.class);

    private final SelenideElement driverSelect = $x("//ng-select[@placeholder = 'Select Driver']//input"),
            btnOpenTransaction = $x("//button[text() = 'Open Transaction ']"),
            descriptionInput = $x("//label[text() = 'Description: ']//..//textarea"),
            btnSave = $x("//button[text() = 'Save ']"),
            btnProcessed = $x("//button[text() = 'Processed ']"),
            modalWindow = $x("//div[@class = 'modal-header']"),
            btnOk = $x("//button[text() = 'OK']"),
            errorMessage = $x("//*[@role='alertdialog']"),
            btnSkip = $x("//button[text() = 'Skip']"),
            transactionStatusProcessed = $x("//*[text() = ' Processed']"),
            btnTake = $x("//button[text() = 'Take ']"),
            descriptionInputProcessed = $x("//*[@role = 'document']//textarea"),
            btnSaveProcess = $x("//*[@role = 'document']//button[text() = 'Save']"),
            driverInfo = $x("//ng-dropdown-panel//*[@role = 'option'][@aria-selected = 'true']"),
            btnDeleteTransaction = $x("//button[contains(text(), 'Delete')]"),
            btnYes = $x("//button[contains(text(), 'Yes')]");
    public ElementsCollection driversList = $$x("//ng-dropdown-panel//*[@role = 'option']");

    @Step("Click on driver select")
    public HosEditorPage clickOnDriverSelect(){
        driverSelect.click();
        LOG.info("Click on driver select");
        return this;
    }

    @Step("Click btn Open transaction")
    public HosEditorPage clickOpenTransactionBtn(){
        btnOpenTransaction.click();
        LOG.info("Click btn Open transaction");
        return this;
    }

    @Step("Input description while create transaction - {description}")
    public HosEditorPage inputDescription(String description){
        descriptionInput.clear();
        descriptionInput.setValue(description);
        descriptionInput.click();
        descriptionInput.pressEnter();
        LOG.info("input description while create transaction - " + description);
        return this;
    }

    @Step("Click btn Save")
    public HosEditorPage clickSaveBtn(){
        btnSave.click();
        LOG.info("Click btn save");
        return this;
    }

    @Step("Click btn processed")
    public HosEditorPage clickProcessedBtn(){
        btnProcessed.click();
        LOG.info("Click btn processed");
        return this;
    }

    @Step("Click btn OK")
    public HosEditorPage clickOkBtn(){
        btnOk.click();
        LOG.info("Click btn ok");
        return this;
    }

    @Step("Click btn Take")
    public HosEditorPage clickTakeBtn(){
        btnTake.click();
        LOG.info("Click btn take");
        return this;
    }

    @Step("Input description while processed transaction - {desc}")
    public HosEditorPage inputDescriptionProcessed(String desc){
        descriptionInputProcessed.clear();
        descriptionInputProcessed.setValue(desc);
        descriptionInputProcessed.click();
        descriptionInputProcessed.pressEnter();
        LOG.info("Input description while processed transaction - " + desc);
        return this;
    }

    @Step("Click btn save processed")
    public HosEditorPage clickBtnSaveProcessed(){
        btnSaveProcess.click();
        LOG.info("Click btn save processed");
        return this;
    }

    @Step("Click btn delete transaction")
    public HosEditorPage clickBtnDeleteTransaction(){
        btnDeleteTransaction.click();
        LOG.info("Click btn delete transaction");
        return this;
    }

    @Step("Click btn Yes")
    public HosEditorPage clickBtnYes(){
        btnYes.click();
        LOG.info("Click btn yes");
        return this;
    }

    @Attachment
    public String getDriverInfo(){
        clickOnDriverSelect();
        String result = driverInfo.getText();
        LOG.info("Getting driver info - " + result);
        return result;
    }

    @Attachment
    public Boolean isBtnTakeTransactionDisplayed(){
        LOG.info("Is btn take transaction displayed - " + btnTake.isDisplayed());
        return btnTake.isDisplayed();
    }

    @Attachment
    public Boolean isBtnOpenTransactionDisplayed(){
        LOG.info("Is btn open transaction displayed - " + btnOpenTransaction.isDisplayed());
        return btnOpenTransaction.isDisplayed();
    }

    @Attachment
    public Boolean isBtnProcessedVisible(){
        LOG.info("Is btn processed transaction visible - " + btnProcessed.is(Condition.visible));
        return btnProcessed.is(Condition.visible);
    }

    @Attachment
    public Boolean isBtnDeleteDisplayed(){
        LOG.info("Is btn delete transaction visible - " + btnDeleteTransaction.isDisplayed());
        return btnDeleteTransaction.isDisplayed();
    }
}
