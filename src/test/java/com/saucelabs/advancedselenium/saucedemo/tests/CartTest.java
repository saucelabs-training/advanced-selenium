package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {

    @Test
    public void addFromProductPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.selectBoltTshirt();

        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        Assertions.assertEquals(1, productPage.cartItems());
    }

    @Test
    public void removeFromProductPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.selectBoltTshirt();
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();

        productPage.removeFromCart();

        Assertions.assertEquals(0, productPage.cartItems());
    }

    @Test
    public void addFromInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();

        inventoryPage.addOnesieToCart();

        Assertions.assertEquals(1, inventoryPage.cartItems());
    }

    @Test
    public void removeFromInventoryPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();

        inventoryPage.addBikeLightToCart();
        inventoryPage.removeBikeLightFromCart();

        Assertions.assertEquals(0, inventoryPage.cartItems());
    }

    @Test
    public void removeFromCartPage() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeBackpackFromCart();

        Assertions.assertEquals(1, cartPage.cartItems());
    }
}
