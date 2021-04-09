package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class InventoryPage {
    private RemoteWebDriver driver;

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return "https://www.saucedemo.com/inventory.html";
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
        return driver.findElement(By.id("add-to-cart-sauce-labs-onesie"));
    }

    public WebElement getCartNumberElement() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public WebElement getAddBikeLightButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
    }

    public WebElement getRemoveBikeLightButton() {
        return driver.findElement(By.id("remove-sauce-labs-bike-light"));
    }

    public List getCartNumberElements() {
        return driver.findElements(By.className("shopping_cart_badge"));
    }

    public WebElement getAddBackpackButton() {
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public WebElement getCartImageLink() {
        return driver.findElement(By.className("shopping_cart_link"));
    }
}
