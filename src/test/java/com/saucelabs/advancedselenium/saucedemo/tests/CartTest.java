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
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getBoltTShirtLink().click();

        ProductPage productPage = new ProductPage(driver);
        productPage.getAddToCartButton().click();

        Assertions.assertEquals("1", productPage.getCartNumberElement().getText());
    }

    @Test
    public void removeFromProductPage() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getBoltTShirtLink().click();

        ProductPage productPage = new ProductPage(driver);
        productPage.getAddToCartButton().click();
        productPage.getRemoveFromCartButton().click();

        Assertions.assertTrue(productPage.getCartNumberElements().isEmpty());
    }

    @Test
    public void addFromInventoryPage() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();

        Assertions.assertEquals("1", inventoryPage.getCartNumberElement().getText());
    }

    @Test
    public void removeFromInventoryPage() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddBikeLightButton().click();
        inventoryPage.getRemoveBikeLightButton().click();

        Assertions.assertTrue(inventoryPage.getCartNumberElements().isEmpty());
    }

    @Test
    public void removeFromCartPage() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddBackpackButton().click();
        inventoryPage.getAddBikeLightButton().click();
        inventoryPage.getCartImageLink().click();

        CartPage cartPage = new CartPage(driver);
        cartPage.getRemoveBackPackButton().click();

        Assertions.assertEquals("1", cartPage.getCartNumberElement().getText());
    }
}
