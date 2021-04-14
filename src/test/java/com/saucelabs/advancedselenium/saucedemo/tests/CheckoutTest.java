package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        new CartPage(driver).checkout();

        InformationPage informationPage = new InformationPage(driver);
        Assertions.assertDoesNotThrow(() ->
                informationPage.submitInfoSuccessfully("Luke", "Perry", "90210"));
    }

    @Test
    public void completeCheckout() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        new CartPage(driver).checkout();
        new InformationPage(driver).submitInfoSuccessfully("Luke", "Perry", "90210");

        Assertions.assertDoesNotThrow(() ->
                new CheckoutPage(driver).finishSuccessfully());
    }
}
