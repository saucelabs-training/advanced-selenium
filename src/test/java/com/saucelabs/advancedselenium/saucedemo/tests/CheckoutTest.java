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
        informationPage.submitForm("Luke", "Perry", "90210");

        Assertions.assertTrue(informationPage.isSubmitSuccessful());
    }

    @Test
    public void completeCheckout() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        new CartPage(driver).checkout();
        new InformationPage(driver).submitForm("Luke", "Perry", "90210");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.finish();

        Assertions.assertTrue(checkoutPage.isCheckoutSuccessful());
        Assertions.assertTrue(new FinishPage(driver).getMessage().contains("Your order has been dispatched"));
    }
}
