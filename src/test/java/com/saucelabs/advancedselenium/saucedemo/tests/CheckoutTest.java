package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.Person;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.CheckoutPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.PageFactory;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        app.login();
        app.addInventory(2);
        InformationPage information = PageFactory.information(driver);
        information.visit();

        Assertions.assertDoesNotThrow(() -> information.submitInfoSuccessfully(new Person()));
    }

    @Test
    public void completeCheckout() {
        app.login();
        app.addInventory(2);
        app.addInformation();
        CheckoutPage checkout = PageFactory.checkout(driver);
        checkout.visit();

        Assertions.assertDoesNotThrow(() ->
                PageFactory.checkout(driver).finishSuccessfully());
    }
}
