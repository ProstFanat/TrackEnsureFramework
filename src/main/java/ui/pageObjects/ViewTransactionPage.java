package ui.pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;

import static com.codeborne.selenide.Selenide.$x;

public class ViewTransactionPage {
    private static final Logger LOG = Logger.getLogger(ViewTransactionPage.class);

    private SelenideElement btnOK = $x("//button[text() = 'ОК']");
}
