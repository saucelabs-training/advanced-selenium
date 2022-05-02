package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.User;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.Product;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = new User();
        validUser.setUsername("standard_user");
        validUser.setPassword("secret_sauce");

        homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword());
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);

        Assertions.assertDoesNotThrow(productPage::addItemToCartSuccessfully);
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCartSuccessfully();

        Assertions.assertDoesNotThrow(productPage::removeItemFromCartSuccessfully);
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assertions.assertDoesNotThrow(() -> inventoryPage.addItemSuccessfully(Product.ONESIE));
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemSuccessfully(Product.BIKE_LIGHT);

        Assertions.assertDoesNotThrow(() -> inventoryPage.removeItemSuccessfully(Product.BIKE_LIGHT));
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemSuccessfully(Product.BACKPACK);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        Assertions.assertDoesNotThrow(() -> cartPage.removeItemSuccessfully(Product.BACKPACK));
    }
}
