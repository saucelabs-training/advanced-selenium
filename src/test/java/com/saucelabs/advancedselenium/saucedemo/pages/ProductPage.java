package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage {
    private RemoteWebDriver driver;

    public ProductPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public ProductPage addToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        return this;
    }

    public ProductPage removeFromCart() {
        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();
        return this;
    }

    public int cartItems() {
        if (driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
        }
    }
}
