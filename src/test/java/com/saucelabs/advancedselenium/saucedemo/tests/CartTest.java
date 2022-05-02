package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.Product;
import com.saucelabs.advancedselenium.saucedemo.pages.ProductPage;

public class CartTest extends BaseTest {
    public void login() {
        HomePage homePage = HomePage.visit(driver);
        homePage.login("standard_user", "secret_sauce");
    }

    @Test
    public void addFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart();

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(1,
                headerSection.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.viewBoltTShirtProduct();

        ProductPage productPage = new ProductPage(driver);
        productPage.addItemToCart();
        productPage.removeItemFromCart();

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem(Product.ONESIE);

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(1,
                headerSection.getNumberItemsInCart(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem(Product.BIKE_LIGHT);

        inventoryPage.removeItem(Product.BIKE_LIGHT);

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }

    @Test
    public void removeFromCartPage() {
        login();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem(Product.BACKPACK);
        inventoryPage.goToCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.removeItem(Product.BACKPACK);

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertEquals(0,
                headerSection.getNumberItemsInCart(),
                "Item not correctly removed from cart");
    }
}
