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

        User lockedOutUser = new User();
        lockedOutUser.setUsername("locked_out_user");
        lockedOutUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully(lockedOutUser.getUsername(), lockedOutUser.getPassword())
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        User validUser = new User();
        validUser.setUsername("standard_user");
        validUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword())
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        User validUser = new User();
        validUser.setUsername("standard_user");
        validUser.setPassword("secret_sauce");

        homePage.loginSuccessfully(validUser.getUsername(), validUser.getPassword());

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
