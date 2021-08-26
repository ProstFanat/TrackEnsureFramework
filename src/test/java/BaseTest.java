import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ui.driver.DriverFactory;

public class BaseTest {
    @BeforeMethod
    public void setup(){
        DriverFactory.initDriver();
    }

    @AfterMethod
    public void quit(){
        Selenide.close();
    }
}
