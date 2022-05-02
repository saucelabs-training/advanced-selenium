package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class InventoryPage {
    public static final String URL = "https://www.saucedemo.com/inventory.html";
    private final RemoteWebDriver driver;

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getMenuButton() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getLogoutLink() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement getBoltTShirtLink() {
        return driver.findElement(By.id("item_1_title_link"));
    }

    public WebElement getAddOnesieButton() {
        return driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']"));
    }

    public WebElement getCartNumberElement() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public WebElement getAddBikeLightButton() {
        return driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bike-light']"));
    }

    public WebElement getRemoveBikeLightButton() {
        return driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bike-light']"));
    }

    public List<WebElement> getCartNumberElements() {
        return driver.findElements(By.className("shopping_cart_badge"));
    }

    public WebElement getAddBackpackButton() {
        return driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']"));
    }

    public WebElement getCartImageLink() {
        return driver.findElement(By.className("shopping_cart_link"));
    }
}
