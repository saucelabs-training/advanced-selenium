package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInLockedOutUser() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginLockedOutUserUnsuccessfully("locked_out_user", "secret_sauce"));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully("standard_user", "secret_sauce"));
    }

    @Test
    public void logout() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();
        homePage.loginSuccessfully("standard_user", "secret_sauce");

        new HeaderSection(driver).logout();

        Assertions.assertTrue(homePage.isOnPage());
    }
}
