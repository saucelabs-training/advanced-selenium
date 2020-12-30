package test.java.pages.address;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import test.java.data.address.User;
import test.java.element.Element;
import test.java.element.TextElement;
import test.java.exceptions.PageValidationException;
import test.java.pages.BasePage;

public class SignInPage extends BasePage {
    public static final String URL = "http://a.testaddressbook.com/sign_in";

    protected TextElement email = browser.textField("Email Field", By.id("session_email"));
    protected TextElement password = browser.textField("Password Field", By.id("session_password"));
    protected Element submit = browser.element("Submit Button", By.cssSelector("[data-test=submit]"));

    public static SignInPage visit() {
        driver.get(URL);
        return new SignInPage();
    }

    public void signInSuccessfully(User user) {
        fillForm(user);
        submit.click();

        try {
            wait.until(driver -> app.isAuthenticated(user));
        } catch (TimeoutException e) {
            throw new PageValidationException("Sign In was not successful after " + DEFAULT_WAIT_TIME + " seconds: ");
        }
    }
}
