package ui.pageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class DriversPage {
    private static final Logger LOG = Logger.getLogger(DriversPage.class);

    private final SelenideElement firstNameInputSearch = $x("//input[@name='driverFirstName']"),
            lastNameInputSearch = $x("//input[@name='driverLastName']"),
            btnUpdateDriver = $x("//a[@uib-tooltip = 'Update']//i[contains(@class, 'fa-pencil')]"),
            btnHosPage = $x("//*[@is-permitted-for = '!read.HOS-DAY-VERIFY']"),
            checkBoxActivateLoadBoard = $x("//*[@ng-if = 'vm.sendLoadOffers']//input"),
            btnSave = $x("//button[contains(text(), 'Save')]"),
            /** Add/update driver window  */
            addNewDriverBtn = $x("//a[contains(text(), 'Add New Driver')]"),
            hosProviderInput = $x("//*[@placeholder = 'HOS Provider']"),
            firstNameInput = $x("//input[@placeholder = 'John']"),
            lastNameInput = $x("//input[@placeholder = 'Smith']"),
            emailInput = $x("//input[@name = 'email'][@placeholder = 'johnsmith@example.com']"),
            loginNameInput = $x("//input[@name = 'loginName'][@placeholder = 'johnsmith@example.com']"),
            passInput = $x("//input[@id = 'button-password']"),
            confirmPassInput = $x("//input[@id = 'button-confirm-password']"),
            driverLicenseNumber = $x("//input[@name = 'licenseNumber']"),
            licenseStateInput = $x("//div[@placeholder = 'Select State']"),
            usaMultidayBasisInput = $x("//div[@name = 'eldMultidayBasisUsed']"),
            canadianMultidayBasisInput = $x("//label[text() = 'Canadian Multiday Basis: ']//..//div[@placeholder = 'Select']"),
            homeTerminalTimezoneInput = $x("//div[@placeholder = 'Select Timezone']"),
            eLogAppModeInput = $x("//label[text() = 'eLog App Mode: ']//..//div[@placeholder = 'Select']"),
            yardMoveCheckBox = $x("//input[@ng-model = 'vm.driver.eldIsYardMoveAllowed']"),
            personalUseCheckBox = $x("//input[@ng-model = 'vm.driver.eldIsPersonalUseAllowed']"),
            regainHoursAtMidnightCheckBox = $x("//input[@ng-model = 'vm.driver.regainHoursAtMidnight']"),
            saveBtn = $x("//button[@ng-click = 'vm.ok(addDriverForm)']"),
            cancelBtn = $x("//*[@class = 'modal-footer']//button[@ng-click = 'vm.cancel()']");
    private final ElementsCollection hosProviders = $$x("//*[@placeholder = 'HOS Provider']//..//li"),
            licenseStatusList = $$x("//div[@placeholder = 'Select State']//..//*[@role = 'option']"),
            usaMultidayList = $$x("//div[@name = 'eldMultidayBasisUsed']//..//*[@role = 'option']"),
            canadianMultidayList = $$x("//label[text() = 'Canadian Multiday Basis: ']//..//*[@role = 'option']"),
            homeTerminalZoneList = $$x("//div[@placeholder = 'Select Timezone']//..//*[@role = 'option']"),
            eLogAppList = $$x("//label[text() = 'eLog App Mode: ']//..//*[@role = 'option']");

    @Step("Input first name search")
    public DriversPage inputFirstNameSearch(String firstName){
        firstNameInputSearch.clear();
        firstNameInputSearch.setValue(firstName);
        LOG.info("input first name - " + firstName);
        return this;
    }

    @Step("Input last name search")
    public DriversPage inputLastNameSearch(String lastName){
        lastNameInputSearch.clear();
        lastNameInputSearch.setValue(lastName);
        LOG.info("input last name - " + lastName);
        return this;
    }

    @Step("Select HOS provider TrackEnsure")
    public DriversPage selectHOSProvider(){
        hosProviderInput.click();
        hosProviders.first().click();
        LOG.info("Select hos provider TrackEnsure");
        return this;
    }

    @Step("Input first name - {firstName}")
    public DriversPage inputFirstName(String firstName){
        firstNameInput.clear();
        firstNameInput.setValue(firstName);
        LOG.info("Input first name = " + firstName);
        return this;
    }

    @Step("Input last name - {lastName}")
    public DriversPage inputLastName(String lastName){
        lastNameInput.clear();
        lastNameInput.setValue(lastName);
        LOG.info("Input last name = " + lastName);
        return this;
    }

    @Step("Input Email - {email}")
    public DriversPage inputEmail(String email){
        emailInput.clear();
        emailInput.setValue(email);
        LOG.info("Input email - " + email);
        return this;
    }

    @Step("Input login name - {loginName}")
    public DriversPage inputLoginName(String loginName){
        loginNameInput.clear();
        loginNameInput.setValue(loginName);
        LOG.info("Input login name - " + loginName);
        return this;
    }

    @Step("Input password - {pass}")
    public DriversPage inputPass(String pass){
        passInput.clear();
        passInput.setValue(pass);
        LOG.info("Input password - " + pass);
        return this;
    }

    @Step("Input confirm password - {pass}")
    public DriversPage inputConfirmPass(String pass){
        confirmPassInput.clear();
        confirmPassInput.setValue(pass);
        LOG.info("Input confirm password - " + pass);
        return this;
    }

    @Step("Input driver license number - {number}")
    public DriversPage inputDriverLicenseNumber(String num){
        driverLicenseNumber.clear();
        driverLicenseNumber.setValue(num);
        LOG.info("Input driver license number - " + num);
        return this;
    }

    @Step("Input first license state")
    public DriversPage inputLicenseState(){
        licenseStateInput.click();
        licenseStatusList.first().click();
        LOG.info("Input first license state");
        return this;
    }

    @Step("Input first USA Multiday Basis")
    public DriversPage inputUSAMultidayBasis(){
        usaMultidayBasisInput.click();
        usaMultidayList.get(1).click();
        LOG.info("Input first USA Multiday Basis");
        return this;
    }

    @Step("Input first canadian Multiday Basis")
    public DriversPage inputCanadianMultidayBasis(){
        canadianMultidayBasisInput.click();
        canadianMultidayList.get(1).click();
        LOG.info("Input first canadian Multiday Basis");
        return this;
    }

    @Step("Input first terminal timezone")
    public DriversPage inputHomeTerminalTimezone(){
        homeTerminalTimezoneInput.click();
        homeTerminalZoneList.first().click();
        LOG.info("Input first terminal timezone");
        return this;
    }

    @Step("Input first Elog app mode")
    public DriversPage inputElogAppMode(){
        eLogAppModeInput.click();
        eLogAppList.first().click();
        LOG.info("Input first Elog app mode");
        return this;
    }

    @Step("Set selected Yard move checkbox - {value}")
    public DriversPage setSelectedCheckBoxYardMove(Boolean value){
        yardMoveCheckBox.setSelected(value);
        LOG.info("Set selected Yard move checkbox - " + value);
        return this;
    }

    @Step("Set selected Personal use checkbox - {value}")
    public DriversPage setSelectedCheckBoxPersonalUse(Boolean value){
        personalUseCheckBox.setSelected(value);
        LOG.info("Set selected Personal use checkbox - " + value);
        return this;
    }

    @Step("Set selected Regain hours at midnight checkbox - {value}")
    public DriversPage setSelectedCheckBoxRegainHoursAtMidnightCheckBox(Boolean value){
        regainHoursAtMidnightCheckBox.setSelected(value);
        LOG.info("Set selected Regain hours at midnight checkbox - " + value);
        return this;
    }

    @Step("Click btn save driver")
    public DriversPage clickBtnSaveDriver(){
        saveBtn.click();
        LOG.info("Click btn save driver");
        return this;
    }

    @Step("Click btn cancel driver")
    public DriversPage cancelDriver(){
        cancelBtn.click();
        LOG.info("Click btn cancel driver");
        return this;
    }

    @Step("Click btn create new driver")
    public DriversPage clickCreateDriverBtn(){
        addNewDriverBtn.click();
        LOG.info("Click btn create new driver");
        return this;
    }

    @Step("Click btn update driver")
    public DriversPage clickUpdateDriverBtn(){
        btnUpdateDriver.click();
        LOG.info("Click btn update driver");
        return this;
    }

    @Step("Click btn Open HOS Page")
    public DriversPage clickOpenHosPageBtn(){
        btnHosPage.click();
        LOG.info("click btn open hos page");
        return this;
    }

    @Attachment
    public Boolean isDriverCreatedMessageDisplayed(String firstName, String lastName){
        BasePage.waitForPageLoaded();
        Boolean isDisplayed = $x(String.format("//*[text() = 'Driver %s %s added successfully!']", firstName, lastName)).isDisplayed();
        LOG.info(String.format("is driver create message displayed - %s", isDisplayed));
        return isDisplayed;
    }
}
