package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage extends BasePage {
    private final By completeElement = By.className("complete-text");

    public FinishPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-complete.html";
    }

    public String getMessage() {
        return driver.findElement(completeElement).getText();
    }
}
