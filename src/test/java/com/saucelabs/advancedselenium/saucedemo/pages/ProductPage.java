package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;

public class ProductPage extends BasePage {
    private final Button addToCartButton = browser.getButton(By.cssSelector("button[data-test^='add-to-cart-']"));
    private final Button removeFromCartButton = browser.getButton(By.cssSelector("button[data-test^='remove']"));

    public ProductPage(SauceDemoApp app) {
        super(app);
    }

    public void addItemToCartSuccessfully() {
        Integer before = app.getNumberItemsInCart();
        Integer expected = before + 1;

        addToCartButton.click();

        try {
            browser.waitUntil(() -> expected.equals(app.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Adding item unsuccessful; ";
            Integer after = app.getNumberItemsInCart();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemFromCartSuccessfully() {
        Integer before = app.getNumberItemsInCart();
        Integer expected = before - 1;

        removeFromCartButton.click();

        try {
            browser.waitUntil(() -> expected.equals(app.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            Integer after = app.getNumberItemsInCart();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }
}
