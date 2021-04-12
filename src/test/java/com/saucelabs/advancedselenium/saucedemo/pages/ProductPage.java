package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage {
    private RemoteWebDriver driver;

    private final By shoppingCartBadgeImage = By.className("shopping_cart_badge");
    private final By addButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By removeButton = By.id("remove-sauce-labs-bolt-t-shirt");

    public ProductPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public ProductPage addToCart() {
        driver.findElement(addButton).click();
        return this;
    }

    public ProductPage removeFromCart() {
        driver.findElement(removeButton).click();
        return this;
    }

    public int cartItems() {
        if (driver.findElements(shoppingCartBadgeImage).isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(driver.findElement(shoppingCartBadgeImage).getText());
        }
    }
}
