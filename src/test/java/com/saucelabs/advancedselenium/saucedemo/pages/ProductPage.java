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
        return driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']"));
    }

    public WebElement getCartNumberElement() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public WebElement getRemoveFromCartButton() {
        return driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bolt-t-shirt']"));
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(By.className("shopping_cart_badge"));
    }
}
