package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(browser);
        homePage.loginSuccessfully();
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(browser);

        Assertions.assertDoesNotThrow(productPage::addItemToCartSuccessfully);
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(browser);
        productPage.addItemToCartSuccessfully();

        Assertions.assertDoesNotThrow(productPage::removeItemFromCartSuccessfully);
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);

        Assertions.assertDoesNotThrow(inventoryPage::addItemSuccessfully);
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();

        Assertions.assertDoesNotThrow(inventoryPage::removeItemSuccessfully);
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(browser);
        Assertions.assertDoesNotThrow(cartPage::removeItemSuccessfully);
    }
}
