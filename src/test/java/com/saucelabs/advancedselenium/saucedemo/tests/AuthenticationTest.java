package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = new HomePage(driver);

        homePage.login("locked_out_user", "secret_sauce");

        Assertions.assertTrue(homePage.getError().contains("Sorry, this user has been locked out"));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);

        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        Assertions.assertTrue(inventoryPage.isOnPage());
    }

    @Test
    public void logout() {
        HomePage homePage = new HomePage(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        inventoryPage.logout();

        Assertions.assertFalse(inventoryPage.isOnPage());
    }
}
