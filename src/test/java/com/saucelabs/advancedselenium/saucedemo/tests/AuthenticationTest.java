package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.data.User;
import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(browser);
        User lockedOutUser = User.lockedOut();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(lockedOutUser)
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(browser);
        User validUser = User.valid();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(validUser)
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(browser);

        homePage.loginSuccessfully();

        HeaderSection headerSection = new HeaderSection(browser);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
