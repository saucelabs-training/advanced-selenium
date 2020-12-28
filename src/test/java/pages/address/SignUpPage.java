package test.java.pages.address;

import test.java.data.address.User;
import test.java.exceptions.PageValidationException;

public class SignUpPage {
    public static SignUpPage visit() {
        throw new PageValidationException("Implement Navigation");
        // return new SignUpPage();
    }

    public void signupSuccessfully(User user) {
        throw new PageValidationException("Implement Signing Up Successfully");
    }
}

