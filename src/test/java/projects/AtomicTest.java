package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.exceptions.PageValidationException;
import test.java.pages.HomePage;

public class AtomicTest extends SauceTestBase {

    @Test
    public void login() {
        HomePage homePage = HomePage.visit();
        homePage.login("standard_user", "secret_sauce");

        try {
            homePage.validateSuccessfulLogin();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }
}
