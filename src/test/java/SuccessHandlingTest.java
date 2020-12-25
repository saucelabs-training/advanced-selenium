package test.java;

import org.junit.Assert;
import org.junit.Test;
import test.java.pages.HomePage;

public class SuccessHandlingTest extends SauceTestBase {

    @Test
    public void testAssertsValue() {
        HomePage homePage = HomePage.visit();
        homePage.login("locked_out_user", "secret_sauce");
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedURL, driver.getCurrentUrl());
    }

    @Test
    public void testAssertsPOBoolean() {
        HomePage homePage = HomePage.visit();
        homePage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(homePage.loginSuccessful());
    }

    @Test
    public void testAssertsBadPOBoolean() throws InterruptedException {
        HomePage homePage = HomePage.visit();
        homePage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(homePage.badLoginSuccessful());
    }

    @Test
    public void testValidatesPOException() {
        HomePage homePage = HomePage.visit();
        homePage.login("locked_out_user", "secret_sauce");

        try {
            homePage.validateSuccessfulLogin();
        } catch (RuntimeException e) {
            Assert.fail("Login Unsuccessful");
        }
    }
}
