package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.Person;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.PageFactory;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        PageFactory.cart(driver).checkout();

        Assertions.assertDoesNotThrow(() ->
                PageFactory.information(driver).submitInfoSuccessfully(new Person()));
    }

    @Test
    public void completeCheckout() {
        InventoryPage inventoryPage = PageFactory.inventory(driver);
        inventoryPage.visit();
        inventoryPage.addBackpackToCart();
        inventoryPage.addBikeLightToCart();
        inventoryPage.goToCart();
        PageFactory.cart(driver).checkout();
        PageFactory.information(driver).submitInfoSuccessfully();

        Assertions.assertDoesNotThrow(() ->
                PageFactory.checkout(driver).finishSuccessfully());
    }
}
