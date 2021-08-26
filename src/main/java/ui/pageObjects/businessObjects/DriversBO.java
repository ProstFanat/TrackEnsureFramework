package ui.pageObjects.businessObjects;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.testng.Assert;
import ui.pageObjects.DriversPage;
import ui.pageObjects.HosPage;

import static com.codeborne.selenide.Selenide.switchTo;

public class DriversBO {
    private static final Logger LOG = Logger.getLogger(CustomersBO.class);
    private final DriversPage driversPage;

    public DriversBO(){
        driversPage = new DriversPage();
    }

    @Step("Create driver firstName - '{firstName}'; lastName - '{lastName}';" +
            " email - '{email}'; login - {login}; pass - {pass}; driverLicenseNumber - {number};" +
            " yardMove {yard}; personalUse - {personalUse}; regainHours - {regainHours}")
    public DriversBO createDriver(String firstName, String lastName, String email, String login,  String pass,
                                    String number, Boolean yard, Boolean personalUse, Boolean regainHours){
        driversPage.clickCreateDriverBtn()
                .selectHOSProvider()
                .inputFirstName(firstName)
                .inputLastName(lastName)
                .inputEmail(email)
                .inputLoginName(login)
                .inputPass(pass)
                .inputConfirmPass(pass)
                .inputDriverLicenseNumber(number)
                .inputLicenseState()
                .inputUSAMultidayBasis()
                .inputCanadianMultidayBasis()
                .inputHomeTerminalTimezone()
                .inputElogAppMode()
                .setSelectedCheckBoxYardMove(yard)
                .setSelectedCheckBoxPersonalUse(personalUse)
                .setSelectedCheckBoxRegainHoursAtMidnightCheckBox(regainHours)
                .clickBtnSaveDriver();
        LOG.info(String.format("Create driver firstName - '%s'; lastName - '%s';" +
                " email - '%s'; login - %s; pass - %s; driverLicenseNumber - %s;" +
                " yardMove %s; personalUse - %s; regainHours - %s", firstName, lastName, email, login, pass, number, yard, personalUse, regainHours));
        return this;
    }

    @Step("Create driver with default parameters")
    public DriversBO createDriverDefaultParameters(){
        int id = (int) (Math.random() * 100000);

        driversPage.clickCreateDriverBtn()
                .selectHOSProvider()
                .inputFirstName("firstName" + id)
                .inputLastName("lastName" + id)
                .inputEmail(String.format("email%s@gmail.com", id))
                .inputLoginName("loginName" + id)
                .inputPass("test")
                .inputConfirmPass("test")
                .inputDriverLicenseNumber("number" + id)
                .inputLicenseState()
                .inputUSAMultidayBasis()
                .inputCanadianMultidayBasis()
                .inputHomeTerminalTimezone()
                .inputElogAppMode()
                .setSelectedCheckBoxYardMove(true)
                .setSelectedCheckBoxPersonalUse(true)
                .setSelectedCheckBoxRegainHoursAtMidnightCheckBox(true)
                .clickBtnSaveDriver();
        LOG.info(String.format("Create driver firstName - 'firstName%s'; lastName - 'lastName%s';" +
                " email - 'email%s@gmail.com'; login - loginName%s; pass - test; driverLicenseNumber - number%s;" +
                " yardMove - true; personalUse - true; regainHours - true", id, id, id, id, id));
        verifyThatDriverCreate("firstName" + id, "lastName" + id);
        return this;
    }

    @Step("Open HOS page for driver {firstName} and {lastName}")
    public HosBO openHosPageForDriver(String firstName, String lastName){
        driversPage.inputFirstNameSearch(firstName)
                .inputLastNameSearch(lastName)
                .clickOpenHosPageBtn();
        switchTo().window(1);
        LOG.info(String.format("Open hos page for %s %s", firstName, lastName));
        return new HosBO();
    }

    @Attachment
    public DriversBO verifyThatDriverCreate(String firstName, String lastName){
        Assert.assertTrue(driversPage.isDriverCreatedMessageDisplayed(firstName, lastName));
        return this;
    }
}
