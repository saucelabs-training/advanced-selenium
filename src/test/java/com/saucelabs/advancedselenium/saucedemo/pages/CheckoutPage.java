package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage extends BasePage {
    private final By finishButton = By.id("finish");

    public CheckoutPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-step-two.html";
    }

    public void finish() {
        driver.findElement(finishButton).click();
    }

    public boolean isCheckoutSuccessful() {
        if (isOnPage()) {
            throw new RuntimeException();
        } else {
            return true;
        }
    }
}
