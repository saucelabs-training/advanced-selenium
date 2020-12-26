package test.java;

import org.junit.Assert;
import org.junit.Test;
import test.java.exceptions.PageValidationException;
import test.java.pages.HomePage;
import test.java.pages.InventoryPage;

public class OpinionatedMethodsTest extends SauceTestBase {

    @Test
    public void successfulLoginValidation() {
        HomePage homePage = HomePage.visit();
        homePage.login("standard_user", "secret_sauce");

        Assert.assertFalse(homePage.loginSuccessful());
    }

    @Test
    public void unsuccessfulLoginValidation() {
        HomePage homePage = HomePage.visit();
        homePage.login("standard_user", "secret_sauce");

        Assert.assertTrue(homePage.loginSuccessful());
    }

    @Test
    public void goToCartInvalidUser() {
        HomePage homePage = HomePage.visit();
        homePage.login("locked_out_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.navigateToShoppingCart();

        validateSuccessfulNavigation(inventoryPage);
    }

    @Test
    public void goToCartOpinionated() {
        HomePage homePage = HomePage.visit();
        homePage.loginSuccessfully("locked_out_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.navigateToShoppingCart();

        validateSuccessfulNavigation(inventoryPage);
    }


    // Validation Methods

    public void validateSuccessfulNavigation(InventoryPage inventoryPage) {
        try {
            inventoryPage.validateSuccessfulNavigation();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }
}
