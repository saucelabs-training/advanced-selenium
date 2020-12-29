package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.data.address.Address;
import test.java.data.address.User;
import test.java.exceptions.PageValidationException;
import test.java.pages.address.*;

public class AddressTest extends SauceTestBase {
    @Test
    public void signUp() {
        SignUpPage signUpPage = SignUpPage.visit();

        try {
            signUpPage.signupSuccessfully();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void logOut() {
        SignUpPage signUpPage = SignUpPage.visit();
        signUpPage.signupSuccessfully();

        try {
            new HomePage().signOutSuccessfully();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void logIn() {
        SignUpPage signUpPage = SignUpPage.visit();
        User user = new User();
        signUpPage.signupSuccessfully(user);
        new HomePage().signOutSuccessfully();

        try {
            new SignInPage().signInSuccessfully(user);
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void createAddress() {
        SignUpPage signUpPage = SignUpPage.visit();
        signUpPage.signupSuccessfully();

        NewAddressPage newAddressPage = NewAddressPage.visit();
        try {
            newAddressPage.createAddressSuccessfully();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void editAddress() {
        SignUpPage signUpPage = SignUpPage.visit();
        signUpPage.signupSuccessfully();
        NewAddressPage newAddressPage = NewAddressPage.visit();
        newAddressPage.createAddressSuccessfully();

        new ReadAddressPage().navigateToEditPage();

        try {
            new EditAddressPage().editAddressSuccessfully();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    @Test
    public void deleteAddress() {
        SignUpPage signUpPage = SignUpPage.visit();
        signUpPage.signupSuccessfully(new User());
        NewAddressPage newAddressPage = NewAddressPage.visit();

        Address address = new Address();
        newAddressPage.createAddressSuccessfully(address);

        ListAddressPage listAddressPage = new ListAddressPage().visit();
        try {
            listAddressPage.deleteAddressSuccessfully(address);
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }
}
