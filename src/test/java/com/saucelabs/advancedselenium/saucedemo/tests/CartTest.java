package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CartTest extends BaseTest {

    @Test
    public void addFromProductPage() {
        app.login();
        ProductPage productPage = PageFactory.product(driver);
        productPage.visit();

        productPage.addToCart();

        Assertions.assertEquals(1, app.cartItems());
    }

    @Test
    public void removeFromProductPage() {
        app.login();
        ProductPage productPage = PageFactory.product(driver);
        productPage.visit();
        productPage.addToCart();

        productPage.removeFromCart();

        Assertions.assertEquals(0, app.cartItems());
    }

    @Test
    public void addFromInventoryPage() {
        app.login();
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();

        inventoryPage.addItemToCart();

        Assertions.assertEquals(1, app.cartItems());
    }

    @Test
    public void removeFromInventoryPage() {
        app.login();
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();

        inventoryPage.addItemToCart();
        inventoryPage.removeItemFromCart();

        Assertions.assertEquals(0, app.cartItems());
    }

    @Test
    public void removeFromCartPage() {
        app.login();
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.addItemToCart();
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();

        PageFactory.cart(driver).removeItemFromCart();

        Assertions.assertEquals(1, app.cartItems());
    }
}
