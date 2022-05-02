package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ProductPage {
    private final RemoteWebDriver driver;
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart-']");
    private final By removeFromCartButton = By.cssSelector("button[data-test^='remove']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public ProductPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCart() {
        driver.findElement(addToCartButton).click();
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = driver.findElements(shoppingCartBadge);
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public void removeItemFromCart() {
        driver.findElement(removeFromCartButton).click();
    }
}
