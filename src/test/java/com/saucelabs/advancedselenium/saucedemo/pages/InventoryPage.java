package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;

public class InventoryPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final Element item1Link = browser.getElement(By.id("item_1_title_link"));
    private final ElementList addItemButtons = browser.getElements(By.cssSelector("button[data-test^='add-to-cart-']"));
    private final ElementList removeItemButtons = browser.getElements(By.cssSelector("button[data-test^='remove-']"));

    public InventoryPage(SauceDemoApp app) {
        super(app);
    }

    public InventoryPage visit() {
        browser.get(URL);
        return this;
    }

    public void viewBoltTShirtProduct() {
        item1Link.click();
    }

    public void addItemSuccessfully() {
        Integer before = app.getNumberItemsInCart();
        Integer expected = before + 1;

        addItemButtons.getRandom().click();

        try {
            browser.waitUntil(() -> expected.equals(app.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Adding item unsuccessful; ";
            String after = app.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
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
