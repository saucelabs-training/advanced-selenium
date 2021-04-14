package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInLockedOutUser() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginLockedOutUserUnsuccessfully("locked_out_user", "secret_sauce"));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully("standard_user", "secret_sauce"));
    }

    @Test
    public void logout() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        homePage.loginSuccessfully("standard_user", "secret_sauce");

        PageFactory.header(driver).logout();

        Assertions.assertTrue(homePage.isOnPage());
    }
}
