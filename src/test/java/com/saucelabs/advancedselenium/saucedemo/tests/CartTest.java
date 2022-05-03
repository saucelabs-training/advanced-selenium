package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    @Test
    public void addFromProductPage() {
        sauceDemoApp.loginSuccessfully();
        InventoryPage inventoryPage = sauceDemoApp.pages().getInventoryPage();
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = sauceDemoApp.pages().getProductPage();
        Assertions.assertDoesNotThrow(productPage::addItemToCartSuccessfully);
    }

    @Test
    public void removeFromProductPage() {
        sauceDemoApp.loginSuccessfully();
        InventoryPage inventoryPage = sauceDemoApp.pages().getInventoryPage();
        inventoryPage.viewBoltTShirtProduct();
        ProductPage productPage = sauceDemoApp.pages().getProductPage();
        productPage.addItemToCartSuccessfully();

        Assertions.assertDoesNotThrow(productPage::removeItemFromCartSuccessfully);
    }

    @Test
    public void addFromInventoryPage() {
        sauceDemoApp.loginSuccessfully();

        InventoryPage inventoryPage = sauceDemoApp.pages().getInventoryPage();
        Assertions.assertDoesNotThrow(inventoryPage::addItemSuccessfully);
    }

    @Test
    public void removeFromInventoryPage() {
        sauceDemoApp.addItemToCart();
        InventoryPage inventoryPage = sauceDemoApp.pages().getInventoryPage().visit();

        Assertions.assertDoesNotThrow(inventoryPage::removeItemSuccessfully);
    }

    @Test
    public void removeFromCartPage() {
        sauceDemoApp.addItemToCart();
        CartPage cartPage = sauceDemoApp.pages().getCartPage().visit();

        Assertions.assertDoesNotThrow(cartPage::removeItemSuccessfully);
    }
}
