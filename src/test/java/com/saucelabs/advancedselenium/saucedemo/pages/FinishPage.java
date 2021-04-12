package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage {
    private RemoteWebDriver driver;

    private final By completeElement = By.className("complete-text");

    public FinishPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return driver.findElement(completeElement).getText();
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html");
    }
}
