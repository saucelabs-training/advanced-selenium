package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage {
    private RemoteWebDriver driver;

    public FinishPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getMessage() {
        return driver.findElement(By.className("complete-text")).getText();
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-complete.html");
    }
}
