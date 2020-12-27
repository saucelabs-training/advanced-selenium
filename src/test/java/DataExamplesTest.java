package test.java;

import org.junit.Assert;
import org.junit.Test;
import test.java.data.Product;
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

    @Test
    public void specificProduct() {
        Product product = new Product(Product.Swag.ONESIE);
        Assert.assertEquals(product.getName(), "Sauce Labs Onesie");
    }

    @Test
    public void constructorProvidesRandomDefault() {
        Product product = new Product();

        try {
            product.getName();
        } catch (NullPointerException e) {
            Assert.fail("Expected product not to be Null");
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void doesNotAllowRepeats() {
        new Product();
        new Product();
        new Product();
        new Product();
        new Product();
        new Product();
        new Product();
    }
}
