package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class CartPage {
    private final RemoteWebDriver driver;

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRemoveBackPackButton() {
        return driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-backpack']"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.cssSelector("button[data-test='checkout']"));
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(By.className("shopping_cart_badge"));
    }
}
