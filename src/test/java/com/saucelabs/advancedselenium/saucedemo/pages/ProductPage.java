package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class ProductPage {
    private final RemoteWebDriver driver;

    public ProductPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getAddToCartButton() {
        return driver.findElement(By.cssSelector("button[data-test='^add-to-cart-']"));
    }

    public WebElement getRemoveFromCartButton() {
        return driver.findElement(By.cssSelector("button[data-test='^remove']"));
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(By.className("shopping_cart_badge"));
    }

    public void addItemToCart() {
        getAddToCartButton().click();
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = getCartNumberElements();
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public void removeItemFromCart() {
        getRemoveFromCartButton().click();
    }
}
