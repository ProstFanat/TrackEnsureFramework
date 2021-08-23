package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class MainScreenPage {
    private static final Logger LOG = Logger.getLogger(MainScreenPage.class);
    SelenideElement cancelShiftBtn = $x("//*[@class = 'modal-footer']//button[@ng-click = 'vm.no()']"),
            infoUserName = $x("//div[@class = 'user-name nav-clip']"),
            btnCustomers = $x("//div[contains(@ng-click, 'customers')]");

    @Step("Click Cancel Shift Btn")
    public MainScreenPage clickCancelShiftBtn(){
        LOG.info(String.format("Is Cancel Shift button exist? -> %s", cancelShiftBtn.exists()));
        if(cancelShiftBtn.exists()){
            cancelShiftBtn.click();
            LOG.info("Cancel Shift button click");
        }
        return this;
    }

    @Step("Click Customers Button")
    public CustomersPage clickCustomersBtn(){
        btnCustomers.click();
        LOG.info("Click Customers Button");
        return new CustomersPage();
    }

    @Step("Is Right user login")
    public boolean isRightUserLogin(String userName){
        boolean isRight = userName.equals(infoUserName.getText().trim());
        LOG.info(String.format("Is right user login -> %s", isRight));
        return isRight;
    }
}
