package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.pages.HomePage;

public class AtomicTest extends SauceTestBase {

    @Test
    public void login() {
        HomePage homePage = HomePage.visit();
        homePage.login("standard_user", "secret_sauce");

        try {
            homePage.validateSuccessfulLogin();
        } catch (RuntimeException e) {
            Assert.fail("Login Unsuccessful");
        }
    }
}
