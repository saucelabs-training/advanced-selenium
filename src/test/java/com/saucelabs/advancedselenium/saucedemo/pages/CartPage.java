package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;

public class CartPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/cart.html";
    private final ElementList removeItemButtons = browser.getElements(By.cssSelector("button[data-test^='remove-']"));

    public CartPage(SauceDemoApp app) {
        super(app);
    }

    public CartPage visit() {
        browser.get(URL);
        return this;
    }

    public void removeItemSuccessfully() {
        Integer before = app.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItemButtons.getRandom().click();

        try {
            browser.waitUntil(() -> expected.equals(app.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            Integer after = app.getNumberItemsInCart();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }
}
