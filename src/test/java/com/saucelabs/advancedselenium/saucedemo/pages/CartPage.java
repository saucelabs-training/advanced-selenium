package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {
    private RemoteWebDriver driver;

    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By shoppingCartBadgeImage = By.className("shopping_cart_badge");
    private final By checkoutButton = By.id("checkout");

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void removeBackpackFromCart() {
        driver.findElement(removeBackpackButton).click();
    }

    public int cartItems() {
        return Integer.parseInt(driver.findElement(shoppingCartBadgeImage).getText());
    }

    public InformationPage checkout() {
        driver.findElement(checkoutButton).click();
        return new InformationPage(driver);
    }
}
