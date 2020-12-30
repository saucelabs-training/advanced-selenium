package test.java.pages.address;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import test.java.data.address.User;
import test.java.element.Element;
import test.java.element.TextElement;
import test.java.exceptions.PageValidationException;
import test.java.pages.BasePage;

public class SignUpPage extends BasePage {
    public static final String URL = "http://a.testaddressbook.com/sign_up";

    protected TextElement email = browser.textField("Email Field", By.id("user_email"));
    protected TextElement password = browser.textField("Password Field", By.id("user_password"));
    protected Element submit = browser.element("Submit Button", By.cssSelector("[data-test=submit]"));

    public static SignUpPage visit() {
        driver.get(URL);
        return new SignUpPage();
    }

    public void signupSuccessfully(User user) {
        fillForm(user);
        submit.click();

        try {
            wait.until(driver -> app.isAuthenticated(user));
        } catch (TimeoutException e) {
            throw new PageValidationException("Sign Up was not successful after " + DEFAULT_WAIT_TIME + " seconds: ");
        }
    }

    public void signupSuccessfully() {
        signupSuccessfully(new User());
    }
}
