package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.User;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.PageFactory;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInLockedOutUser() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginLockedOutUserUnsuccessfully(User.lockedOut()));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(User.valid()));
    }

    @Test
    public void logout() {
        app.login();
        PageFactory.inventory(driver).visit();

        PageFactory.header(driver).logout();

        Assertions.assertFalse(app.isAuthenticated());
    }
}
