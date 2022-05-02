package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.Product;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    public InventoryPage login() {
        HomePage homePage = HomePage.visit(driver);
        return homePage.login("standard_user", "secret_sauce");
    }

    @Test
    public void addFromProductPage() {
        InventoryPage inventoryPage = login();
        ProductPage productPage = inventoryPage.viewBoltTShirtProduct();

        productPage.addItemToCart();

        Assertions.assertEquals(1,
                productPage.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        InventoryPage inventoryPage = login();
        ProductPage productPage = inventoryPage.viewBoltTShirtProduct();

        productPage.addItemToCart();
        productPage.removeItemFromCart();

        Assertions.assertEquals(0,
                productPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem(Product.ONESIE);

        Assertions.assertEquals(1,
                inventoryPage.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromInventoryPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem(Product.BIKE_LIGHT);

        inventoryPage.removeItem(Product.BIKE_LIGHT);

        Assertions.assertEquals(0,
                inventoryPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void removeFromCartPage() {
        InventoryPage inventoryPage = login();
        inventoryPage.addItem(Product.BACKPACK);
        CartPage cartPage = inventoryPage.goToCart();

        cartPage.removeItem(Product.BACKPACK);

        Assertions.assertEquals(0,
                cartPage.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }
}
