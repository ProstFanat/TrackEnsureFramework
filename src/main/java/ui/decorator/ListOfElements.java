package ui.decorator;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

public class ListOfElements {

    private static final Logger LOG = Logger.getLogger(ListOfElements.class);

    @Step("Click on entity '{entity}' from list of select")
    public static void clickOnEntityFromList(String entity, ElementsCollection list){
        boolean isFind = false;
        LOG.info("Looking for desired Entity");
        for (SelenideElement currentEntity: list) {
            if (currentEntity.getText().trim().equals(entity)){
                currentEntity.click();
                LOG.info("Click on the desired entity - " + entity);
                isFind = true;
                break;
            }
        }
        if(!isFind) LOG.info("Desired entity not found");
    }
}
