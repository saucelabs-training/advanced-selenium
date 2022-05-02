package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginUnsuccessfully("locked_out_user", "secret_sauce")
        );
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = HomePage.visit(driver);

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully("standard_user", "secret_sauce")
        );
    }

    @Test
    public void logout() {
        HomePage homePage = HomePage.visit(driver);
        homePage.loginSuccessfully("standard_user", "secret_sauce");

        HeaderSection headerSection = new HeaderSection(driver);
        Assertions.assertDoesNotThrow(headerSection::logOutSuccessfully);
    }
}
