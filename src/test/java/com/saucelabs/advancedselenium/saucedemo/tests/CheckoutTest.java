package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.Person;
import com.saucelabs.advancedselenium.saucedemo.pages.CheckoutPage;
import com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        sauceDemoApp.addItemToCart();
        InformationPage informationPage = sauceDemoApp.pages().getInformationPage().visit();

        Assertions.assertDoesNotThrow(() -> informationPage.addInformationSuccessfully(new Person()));
    }

    @Test
    public void completeCheckout() {
        sauceDemoApp.addItemToCart();
        InformationPage informationPage = sauceDemoApp.pages().getInformationPage().visit();
        informationPage.addInformationSuccessfully();

        CheckoutPage checkoutPage = sauceDemoApp.pages().getCheckoutPage();
        Assertions.assertDoesNotThrow(checkoutPage::finishSuccessfully);
    }
}
