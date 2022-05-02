package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.CheckoutPage;
import com.saucelabs.advancedselenium.saucedemo.pages.FinishPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import com.saucelabs.advancedselenium.saucedemo.pages.Product;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(driver);
        homePage.login("standard_user", "secret_sauce");
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItem(Product.ONESIE);
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);

        informationPage.addInformation("Luke", "Perry", "90210");

        Assertions.assertDoesNotThrow(informationPage::validateInformationAdded);
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.addInformation("Luke", "Perry", "90210");

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.finish();

        Assertions.assertDoesNotThrow(checkoutPage::validateFinished);
    }
}
