package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;

public class CheckoutPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final Button finishButton = browser.getButton("finish");

    public CheckoutPage(SauceDemoApp app) {
        super(app);
    }

    public void finishSuccessfully() {
        finishButton.click();
        try {
            browser.waitUntil(() -> !URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            FinishPage finishPage = new FinishPage(app);
            if (!finishPage.isComplete()) {
                throw new PageValidationException("Checkout unsuccessful;");
            }
        }
    }
}
