package ui.dataProvider;

import org.testng.annotations.DataProvider;

public class ScreenshotUrls {
    @DataProvider(name = "valid-urls")
    public static Object[][] validUrls(){
        return new String[][]{
                {"http://trackensure.com"},
                {"https://trackensure.com"},
                {"www.trackensure.com"}
        };
    }

    @DataProvider(name = "invalid-urls")
    public static Object[][] invalidUrls(){
        return new String[][]{
                {"http://trackensure"},
                {"https://trackensure"},
                {"www.trackensure"},
                {"test"}
        };
    }
}
