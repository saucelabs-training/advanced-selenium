package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.Person;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.CheckoutPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class CheckoutTest extends BaseTest {

    public void login() {
        HomePage homePage = HomePage.visit(browser);

        homePage.loginSuccessfully();
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(browser);
        inventoryPage.addItemSuccessfully();
        inventoryPage.goToCart();
        CartPage cartPage = new CartPage(browser);
        cartPage.checkout();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(browser);

        Person validPerson = new Person();
        Assertions.assertDoesNotThrow(() -> informationPage.addInformationSuccessfully(validPerson));
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(browser);
        informationPage.addInformationSuccessfully();

        CheckoutPage checkoutPage = new CheckoutPage(browser);
        Assertions.assertDoesNotThrow(checkoutPage::finishSuccessfully);
    }
}
