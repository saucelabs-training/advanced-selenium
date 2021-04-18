package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.util.function.Function;

public class CheckoutPage extends BasePage {
    private final By finishButton = By.id("finish");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public CheckoutPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-step-two.html";
    }

    public void finishSuccessfully() {
        getElement("finishButton").click();
        try {
            wait.until((Function<WebDriver, Object>) driver -> !isOnPage());
            if ((new FinishPage(driver)).getMessage().contains("Your order has been dispatched")) {
                return;
            }
        } catch (TimeoutException ignored) {
        }
        String error = getElement("errorElement").getText();
        throw new PageValidationException("Finishing Checkout was not successful: " + error);
    }
}
