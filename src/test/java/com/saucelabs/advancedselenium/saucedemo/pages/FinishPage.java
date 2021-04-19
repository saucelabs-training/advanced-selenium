package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class FinishPage extends BasePage {
    private final Element completeElement = getElement(By.className("complete-text"), "Complete Element");

    public FinishPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-complete.html";
    }

    public String getMessage() {
        return completeElement.getText();
    }
}
