package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        InventoryPage inventoryPage = new InventoryPage(driver).visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        CartPage cartPage = inventoryPage.goToCart();
        InformationPage informationPage = cartPage.checkout();

        CheckoutPage checkoutPage = informationPage.submitForm("Luke", "Perry", "90210");

        Assertions.assertTrue(checkoutPage.isOnPage());
    }

    @Test
    public void completeCheckout() {
        InventoryPage inventoryPage = new InventoryPage(driver).visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        CartPage cartPage = inventoryPage.goToCart();
        InformationPage informationPage = cartPage.checkout();
        CheckoutPage checkoutPage = informationPage.submitForm("Luke", "Perry", "90210");

        FinishPage finishPage = checkoutPage.finish();

        Assertions.assertTrue(finishPage.isOnPage());
        Assertions.assertTrue(finishPage.getMessage().contains("Your order has been dispatched"));
    }
}
