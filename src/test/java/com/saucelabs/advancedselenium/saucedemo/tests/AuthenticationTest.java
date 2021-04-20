package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();

        homePage.login("locked_out_user", "secret_sauce");

        PageValidationException ex = Assertions.assertThrows(PageValidationException.class, homePage::validateLoginSuccessful);
        Assertions.assertTrue(ex.getMessage().contains("Sorry, this user has been locked out"));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();

        homePage.login("standard_user", "secret_sauce");

        Assertions.assertDoesNotThrow(homePage::validateLoginSuccessful);
    }

    @Test
    public void logout() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();
        homePage.login("standard_user", "secret_sauce");

        new HeaderSection(driver).logout();

        Assertions.assertTrue(homePage.isOnPage());
    }
}
