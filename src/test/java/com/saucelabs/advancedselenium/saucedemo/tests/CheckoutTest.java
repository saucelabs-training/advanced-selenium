package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        CartPage cartPage = inventoryPage.goToCart();
        InformationPage informationPage = cartPage.checkout();

        CheckoutPage checkoutPage = informationPage.submitForm("Luke", "Perry", "90210");

        Assertions.assertTrue(checkoutPage.isOnPage());
    }

    @Test
    public void completeCheckout() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");
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
