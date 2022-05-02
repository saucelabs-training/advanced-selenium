package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public class InventoryPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final By item1Link = By.id("item_1_title_link");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void viewBoltTShirtProduct() {
        driver.findElement(item1Link).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }

    public void addItemSuccessfully(Product product) {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before + 1;

        addItem(product);

        Integer after = headerSection.getNumberItemsInCart();
        if (!Objects.equals(after, expected)) {
            throw new PageValidationException("Adding item unsuccessful; Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemSuccessfully(Product product) {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItem(product);

        Integer after = headerSection.getNumberItemsInCart();
        if (!Objects.equals(after, expected)) {
            throw new PageValidationException("Removing item unsuccessful; Expected: " + expected + ", but found: " + after);
        }
    }

    private void addItem(Product product) {
        String cssSelector = "button[data-test='add-to-cart-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    private void removeItem(Product product) {
        String cssSelector = "button[data-test='remove-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }
}
