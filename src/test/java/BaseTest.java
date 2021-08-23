import org.testng.annotations.BeforeClass;
import ui.driver.DriverFactory;

public class BaseTest {
    @BeforeClass
    public void setup(){
        DriverFactory.initDriver();
    }
}
