package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.User;
import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = sauceDemoApp.pages().getHomePage().visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(User.lockedOut())
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = sauceDemoApp.pages().getHomePage().visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(User.valid())
        );
    }

    @Test
    public void logout() {
        sauceDemoApp.loginSuccessfully();

        HeaderSection headerSection = sauceDemoApp.pages().getHeaderSection();
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
