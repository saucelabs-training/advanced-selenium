package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.pages.CheckoutSignIn;
import test.java.pages.HomePage;
import test.java.pages.InformationPage;
import test.java.pages.InventoryPage;

public class CoupledPOsTest extends SauceTestBase {

    @Test
    public void coupled() {
        Assert.assertTrue(
                new HomePage(driver)
                        .navigateTo()
                        .login("standard_user", "secret_sauce")
                        .selectProduct("Sauce Labs Bolt T-Shirt")
                        .selectProduct("Sauce Labs Fleece Jacket")
                        .shoppingCart()
                        .checkOut()
                        .addInformation()
                        .finish()
                        .isSuccessful());
    }

    @Test
    public void loginCheckout() {
        Assert.assertTrue(
                new InventoryPage(driver)
                        .navigateTo()
                        .selectProduct("Sauce Labs Bolt T-Shirt")
                        .selectProduct("Sauce Labs Fleece Jacket")
                        .shoppingCart()
                        // What about a site that required a login on the shopping cart if not already logged in?
                        .checkOutSignIn()
                        // What if that login was the same implementation just a different navigation?
                        .loginToCart("standard_user", "secret_sauce")
                        .addInformation()
                        .finish()
                        .isSuccessful());
    }

    @Test
    public void twoChains() {
        new InventoryPage(driver)
                .navigateTo()
                .selectProduct("Sauce Labs Bolt T-Shirt")
                .selectProduct("Sauce Labs Fleece Jacket")
                .shoppingCart()
                .checkOut();

        CheckoutSignIn checkoutSignIn = new CheckoutSignIn();
        checkoutSignIn.loginToCart("standard_user", "secret_sauce");

        Assert.assertTrue(
                new InformationPage()
                        .addInformation()
                        .finish()
                        .isSuccessful());
    }
}
