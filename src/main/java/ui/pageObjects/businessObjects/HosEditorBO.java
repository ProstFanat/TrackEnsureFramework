package ui.pageObjects.businessObjects;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.jsoup.Connection;
import ui.pageObjects.BasePage;
import ui.pageObjects.HosEditorPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.url;

public class HosEditorBO {
    private static final Logger LOG = Logger.getLogger(MainScreenBO.class);
    private final HosEditorPage hosEditorPage;

    public HosEditorBO(){
        hosEditorPage = new HosEditorPage();
    }

    @Step("Processed transaction")
    public HosEditorBO processTransaction(String comment){
        if(hosEditorPage.isBtnTakeTransactionDisplayed()) hosEditorPage.clickTakeBtn();
        hosEditorPage.clickProcessedBtn()
                .inputDescriptionProcessed(comment)
                .clickBtnSaveProcessed();
        LOG.info("Transaction has been processed!");
        return this;
    }

    @Attachment
    public Map<String, String> createAndProcessedTransactionWithReturningUrlAndDriverName(String description){
        int createdTransactions = 0, counter = 1;
        Map<String, String> result = new HashMap<String, String>();
        while(createdTransactions < 1){
            hosEditorPage.clickOnDriverSelect()
                    .driversList.get(counter).click();
            BasePage.waitForPageLoaded();
            try {
                if (hosEditorPage.isBtnOpenTransactionDisplayed()) {
                    hosEditorPage.clickOpenTransactionBtn()
                            .inputDescription(description)
                            .clickSaveBtn();
                    BasePage.waitForPageLoaded();
                    processTransaction(description);

                    createdTransactions++;

                    result.put("url", url());
                    result.put("name", hosEditorPage.getDriverInfo());
                    System.out.println(result.get("url"));
                    System.out.println(result.get("name"));
                } else if (hosEditorPage.isBtnProcessedVisibleDisplayed()) {
                    processTransaction(description);
                    BasePage.waitForPageLoaded();
                    createdTransactions++;

                    result.put("url", url());
                    result.put("name", hosEditorPage.getDriverInfo());
                    System.out.println(result.get("url"));
                    System.out.println(result.get("name"));
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            counter++;
            if(createdTransactions > 0) break;
        }
        return result;
    }

}
