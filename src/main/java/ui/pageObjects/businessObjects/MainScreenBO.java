package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.Assert;
import ui.pageObjects.MainScreenPage;

public class MainScreenBO {
    private static final Logger LOG = Logger.getLogger(MainScreenBO.class);
    private final MainScreenPage mainScreenPage;

    public MainScreenBO(){
        mainScreenPage = new MainScreenPage();
    }

    @Step("Verify that right user login - {userName}")
    public MainScreenBO verifyThatRightUserNameDisplayed(String userName){
        Assert.assertTrue(mainScreenPage.isRightUserLogin(userName), "Another User login");
        return this;
    }

    @Step("Open Customers page")
    public CustomersBO openCustomersPage(){
        mainScreenPage.clickCustomersBtn();
        LOG.info("Customers page opened");
        return new CustomersBO();
    }
}
