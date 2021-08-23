import org.testng.annotations.Test;
import ui.pageObjects.businessObjects.LoginBO;
import utils.PropertiesReader;

public class TestPage extends BaseTest{

    @Test
    public void test(){
        new LoginBO().loginToTrackEnsure()
                    .verifyThatRightUserNameDisplayed(PropertiesReader.getProperty("USER_NAME"));
    }
}
