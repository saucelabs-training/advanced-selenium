package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage {
    private RemoteWebDriver driver;

    public CheckoutPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return "https://www.saucedemo.com/checkout-step-two.html";
    }

    public WebElement getFinishButton() {
        return driver.findElement(By.id("finish"));
    }
}
