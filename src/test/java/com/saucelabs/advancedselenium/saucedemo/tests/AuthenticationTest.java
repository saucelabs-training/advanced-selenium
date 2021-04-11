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
        User lockedOutUser = new User();
        lockedOutUser.setUser("locked_out_user");
        lockedOutUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginLockedOutUserUnsuccessfully(lockedOutUser));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        User validUser = new User();
        validUser.setUser("standard_user");
        validUser.setPassword("secret_sauce");

        Assertions.assertDoesNotThrow(() ->
                homePage.loginSuccessfully(validUser));
    }

    @Test
    public void logout() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        User validUser = new User();
        validUser.setUser("standard_user");
        validUser.setPassword("secret_sauce");

        homePage.loginSuccessfully(validUser);

        PageFactory.header(driver).logout();

        Assertions.assertTrue(homePage.isOnPage());
    }
}
