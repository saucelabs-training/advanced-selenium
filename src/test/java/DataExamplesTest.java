package test.java;

import org.junit.Test;
import test.java.data.User;
import test.java.pages.HomePage;

public class DataExamplesTest extends SauceTestBase {

    @Test
    public void justStrings() {
        String username = "standard_user";
        String password = "secret_sauce";

        HomePage homePage = new HomePage();
        homePage.loginSuccessfully(username, password);
    }

    @Test
    public void dataClass() {
        User validUser = new User("standard_user", "secret_sauce");

        HomePage homePage = new HomePage();
        homePage.loginSuccessfully(validUser);
    }

    @Test
    public void staticMehod() {
        User validUser = User.valid();

        HomePage homePage = new HomePage();
        homePage.loginSuccessfully(validUser);
    }

    @Test
    public void randomData() {
        User newUser = User.random();

        // Example Only; not a real feature
        HomePage homePage = new HomePage();
        homePage.signUpNewUser(newUser);
    }

    @Test
    public void specialData() {
        User blankPassword = User.blankPassword();

        HomePage homePage = new HomePage();
        homePage.loginUnsuccessfully(blankPassword);
    }
}
