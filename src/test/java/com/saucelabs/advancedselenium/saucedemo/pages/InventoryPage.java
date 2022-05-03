package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;

import java.util.Random;
import java.util.function.Function;

public class InventoryPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final Element item1Link = new Element(driver, By.id("item_1_title_link"));
    private final Element shoppingCartLink = new Element(driver, By.className("shopping_cart_link"));
    private final ElementList addItemButtons = new ElementList(driver, By.cssSelector("button[data-test^='add-to-cart-']"));
    private final ElementList removeItemButtons = new ElementList(driver, By.cssSelector("button[data-test^='remove-']"));

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void viewBoltTShirtProduct() {
        item1Link.click();
    }

    public void goToCart() {
        shoppingCartLink.click();
    }

    public void addItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before + 1;

        addItemButtons.getRandom().click();

        try {
            wait.until((Function<WebDriver, Object>) driver -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Adding item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItemButtons.getRandom().click();

        try {
            wait.until((Function<WebDriver, Object>) driver -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }
}
