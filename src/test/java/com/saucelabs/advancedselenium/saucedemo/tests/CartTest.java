package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CartTest extends BaseTest {

    @Test
    public void addFromProductPage() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.selectItem();

        PageFactory.product(driver).addToCart();

        Assertions.assertEquals(1, PageFactory.header(driver).cartItems());
    }

    @Test
    public void removeFromProductPage() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.selectItem();
        ProductPage productPage = PageFactory.product(driver);
        productPage.addToCart();

        productPage.removeFromCart();

        Assertions.assertEquals(0, PageFactory.header(driver).cartItems());
    }

    @Test
    public void addFromInventoryPage() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();

        inventoryPage.addItemToCart();

        Assertions.assertEquals(1, PageFactory.header(driver).cartItems());
    }

    @Test
    public void removeFromInventoryPage() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();

        inventoryPage.addItemToCart();
        inventoryPage.removeItemFromCart();

        Assertions.assertEquals(0, PageFactory.header(driver).cartItems());
    }

    @Test
    public void removeFromCartPage() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.addItemToCart();
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();

        PageFactory.cart(driver).removeItemFromCart();

        Assertions.assertEquals(1, PageFactory.header(driver).cartItems());
    }
}
