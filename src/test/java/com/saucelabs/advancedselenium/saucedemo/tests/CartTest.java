package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = new HomePage(driver);
        driver.get(HomePage.URL);
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getBoltTShirtLink().click();

        ProductPage productPage = new ProductPage(driver);
        productPage.getAddToCartButton().click();

        Assertions.assertEquals("1",
                productPage.getCartNumberElement().getText(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getBoltTShirtLink().click();
        ProductPage productPage = new ProductPage(driver);
        productPage.getAddToCartButton().click();

        productPage.getRemoveFromCartButton().click();

        Assertions.assertTrue(productPage.getCartNumberElements().isEmpty(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();

        Assertions.assertEquals("1",
                inventoryPage.getCartNumberElement().getText());
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddBikeLightButton().click();

        inventoryPage.getRemoveBikeLightButton().click();

        Assertions.assertTrue(inventoryPage.getCartNumberElements().isEmpty(),
                "Shopping Cart is not empty");
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddBackpackButton().click();
        inventoryPage.getCartImageLink().click();

        CartPage cartPage = new CartPage(driver);
        cartPage.getRemoveBackPackButton().click();

        Assertions.assertTrue(cartPage.getCartNumberElements().isEmpty(),
                "Shopping Cart is not empty");
    }
}
