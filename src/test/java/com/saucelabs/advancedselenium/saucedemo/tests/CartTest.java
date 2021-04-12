package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {

    @Test
    public void addFromProductPage() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");
        ProductPage productPage = inventoryPage.selectBoltTshirt();

        productPage.addToCart();

        Assertions.assertEquals(1, productPage.cartItems());
    }

    @Test
    public void removeFromProductPage() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");
        ProductPage productPage = inventoryPage.selectBoltTshirt();
        productPage.addToCart();

        productPage.removeFromCart();

        Assertions.assertEquals(0, productPage.cartItems());
    }

    @Test
    public void addFromInventoryPage() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        inventoryPage.addOnesieToCart();

        Assertions.assertEquals(1, inventoryPage.cartItems());
    }

    @Test
    public void removeFromInventoryPage() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        inventoryPage.addBikeLightToCart();
        inventoryPage.removeBikeLightFromCart();

        Assertions.assertEquals(0, inventoryPage.cartItems());
    }

    @Test
    public void removeFromCartPage() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        CartPage cartPage = inventoryPage.goToCart();

        cartPage.removeBackpackFromCart();

        Assertions.assertEquals(1, cartPage.cartItems());
    }
}
