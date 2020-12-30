package test.java.pages.address;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.data.address.User;
import test.java.element.Element;
import test.java.element.TextElement;
import test.java.exceptions.PageValidationException;
import test.java.pages.BasePage;

import java.util.function.Function;

public class SignInPage extends BasePage {
    public static final String URL = "http://a.testaddressbook.com/sign_up";

    protected TextElement email = browser.textField("Email Field", By.id("session_email"));
    protected TextElement password = browser.textField("Password Field", By.id("session_password"));
    protected Element submit = browser.element("Submit Button", By.cssSelector("[data-test=submit]"));

    public void signInSuccessfully(User user) {
        fillForm(user);
        submit.click();

        try {
            wait.until((Function<WebDriver, Object>) driver -> !isOnPage());
        } catch (TimeoutException e) {
            throw new PageValidationException("Sign In was not successful after " + DEFAULT_WAIT_TIME + " seconds: ");
        }
    }

    public boolean isOnPage() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(URL) || currentUrl.equals("http://a.testaddressbook.com/session");
    }
}
