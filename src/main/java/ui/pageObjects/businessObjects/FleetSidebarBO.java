package ui.pageObjects.businessObjects;

import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import ui.pageObjects.FleetSidebarPage;

public class FleetSidebarBO {
    private static final Logger LOG = Logger.getLogger(MainScreenBO.class);
    private final FleetSidebarPage fleetSidebarPage;

    public FleetSidebarBO(){
        fleetSidebarPage = new FleetSidebarPage();
    }

    @Step("Open Assets/Drivers page")
    public DriversBO openDriversPage(){
        fleetSidebarPage.clickAssetsBtn().clickAssetsBtn()
                .clickDriversBtn();
        LOG.info("Open Assets/Drivers page");
        return new DriversBO();
    }
}
