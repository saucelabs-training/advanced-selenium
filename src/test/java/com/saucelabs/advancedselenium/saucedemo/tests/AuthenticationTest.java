package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.User;
import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);
        User lockedOutUser = User.lockedOut();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(lockedOutUser.getUsername(), lockedOutUser.getPassword())
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = User.valid();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword())
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = User.valid();

        homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword());

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
