package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {
    private RemoteWebDriver driver;

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getRemoveBackPackButton() {
        return driver.findElement(By.id("remove-sauce-labs-backpack"));
    }

    public WebElement getCartNumberElement() {
        return driver.findElement(By.className("shopping_cart_badge"));
    }

    public WebElement getCheckoutButton() {
        return driver.findElement(By.id("checkout"));
    }
}
