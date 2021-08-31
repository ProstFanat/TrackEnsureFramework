package ui.pageObjects.businessObjects;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.BasePage;
import ui.pageObjects.HosEditorPage;
import utils.PropertiesReader;

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

    @Step("Delete transaction")
    public HosEditorBO deleteTransaction(){
        if(hosEditorPage.isBtnDeleteDisplayed()){
            hosEditorPage.clickBtnDeleteTransaction()
                    .clickBtnYes();
            BasePage.waitForPageLoaded();
            LOG.info("Transaction deleted");
        } else LOG.info("Btn Delete Transaction is not displayed");
        return this;
    }

    @Step("Open Transaction")
    public HosEditorBO openTransaction(String description){
        deleteTransaction();
        if (hosEditorPage.isBtnOpenTransactionDisplayed()) {
            hosEditorPage.clickOpenTransactionBtn()
                    .inputDescription(description)
                    .clickSaveBtn();
            LOG.info("Transaction opened");
            BasePage.waitForPageLoaded();
        } else {
            LOG.info("Transaction was already opened");
        }

        return this;
    }

    @Attachment
    public String createAndProcessedTransactionWithReturningDriverName(String description){
        int createdTransactions = 0, counter = 1;
        String result = null;
        while(createdTransactions < 1){
            hosEditorPage.clickOnDriverSelect()
                    .driversList.get(counter).click();
            BasePage.waitForPageLoaded();
            try {
                deleteTransaction();
                if (hosEditorPage.isBtnOpenTransactionDisplayed()) {
                    hosEditorPage.clickOpenTransactionBtn()
                            .inputDescription(description)
                            .clickSaveBtn();
                    BasePage.waitForPageLoaded();
                    processTransaction(description);

                    createdTransactions++;

                    result = hosEditorPage.getDriverInfo();
                } else if (hosEditorPage.isBtnProcessedVisible()) {
                    processTransaction(description);
                    BasePage.waitForPageLoaded();
                    createdTransactions++;

                    result = hosEditorPage.getDriverInfo();
                } else if (hosEditorPage.isBtnTakeTransactionDisplayed()) {
                    BasePage.waitForPageLoaded();
                    processTransaction(description);
                    createdTransactions++;
                    result = hosEditorPage.getDriverInfo();
                    LOG.info(String.format("Number of created transactions - %s", createdTransactions));
                }
            } catch (Exception e) {
                LOG.info(e);
            }
            counter++;
            if(createdTransactions > 0) break;

            if(hosEditorPage.clickOnDriverSelect().driversList.size() - 1 == counter){
                return createTransactionForNewDriver();
            }
        }
        return result;
    }

    @Step("creating {quantity} transactions")
    public HosEditorBO createTransactions(Integer quantity, String description){
        int createdTransactions = 0, counter = 1;
        while(createdTransactions < quantity){
            System.out.println("counter = " + counter + "  createdTransactions = " + createdTransactions);
            hosEditorPage.clickOnDriverSelect()
                    .driversList.get(counter).click();
            try {
                deleteTransaction();
                if (hosEditorPage.isBtnOpenTransactionDisplayed()) {
                    hosEditorPage.clickOpenTransactionBtn()
                            .inputDescription(description)
                            .clickSaveBtn();
                    BasePage.waitForPageLoaded();
                    processTransaction(description);
                    createdTransactions++;
                    LOG.info(String.format("Number of created transactions - %s", createdTransactions));
                } else if (hosEditorPage.isBtnProcessedVisible()) {
                    BasePage.waitForPageLoaded();
                    processTransaction(description);
                    createdTransactions++;
                    LOG.info(String.format("Number of created transactions - %s", createdTransactions));
                } else if (hosEditorPage.isBtnTakeTransactionDisplayed()) {
                    BasePage.waitForPageLoaded();
                    processTransaction(description);
                    createdTransactions++;
                    LOG.info(String.format("Number of created transactions - %s", createdTransactions));
                }
            } catch (Exception e){
                LOG.info(e);
            }
            counter++;
        }
        return this;
    }

    @Step("Create transaction for new driver")
    public String createTransactionForNewDriver(){
        new FleetSidebarBO().openDriversPage();
        Integer driverId = new DriversBO().createDriver();
        new DriversBO().openHosPageForDriver("FirstName" + driverId, "LastName" + driverId)
                .openHosEditorPage()
                .openTransaction(PropertiesReader.getProperty("DESCRIPTION"))
                .processTransaction(PropertiesReader.getProperty("DESCRIPTION"));
        return String.format("FirstName%s LastName%s", driverId, driverId);
    }
}
