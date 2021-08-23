package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.CustomersPage;

public class CustomersBO {
    private static final Logger LOG = Logger.getLogger(CustomersBO.class);
    private final CustomersPage customersPage;

    public CustomersBO(){
        customersPage = new CustomersPage();
    }

    @Step("Login As {orgName}")
    public CustomersBO loginAsOrg(String orgName){
        customersPage.inputOrgName(orgName)
                .clickSearchBtn()
                .clickLogAsOrgBtn();
        LOG.info(String.format("Logged in as %s", orgName));
        return this;
    }
}
