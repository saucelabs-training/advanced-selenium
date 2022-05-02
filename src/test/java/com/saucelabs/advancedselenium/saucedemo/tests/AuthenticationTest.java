package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);

        homePage.login("locked_out_user", "secret_sauce");

        Assertions.assertTrue(homePage.isLockedOut(), "Error Not Found");
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        Assertions.assertTrue(inventoryPage.isOnPage(), "Login Not Successful");
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        InventoryPage inventoryPage = homePage.login("standard_user", "secret_sauce");

        inventoryPage.logOut();

        Assertions.assertTrue(homePage.isOnPage(), "Logout Not Successful");
    }
}
