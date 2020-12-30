package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.data.User;
import test.java.element.ButtonElement;
import test.java.element.Element;
import test.java.element.TextElement;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class HomePage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    protected TextElement username = browser.textField("Username Field", By.id("user-name"));
    protected TextElement password = browser.textField("Password Field", By.id("password"));
    protected ButtonElement submitButton = browser.button("Submit Button", By.id("login-button"));
    protected Element errorMessage = browser.element("Error Message", By.cssSelector("[data-test=error]"));

    public static HomePage visit() {
        HomePage homePage = new HomePage();
        homePage.navigateTo();
        return homePage;
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public void loginSuccessfully() {
        loginSuccessfully(User.valid());
    }

    public void loginSuccessfully(User user) {
        fillForm(User.valid());
        submitButton.click();
        validateSuccessfulLogin();
    }

    public void login(String uname, String pwd) {
        username.sendKeys(uname);
        username.sendKeys(pwd);
        submitButton.click();
    }

    public boolean loginSuccessful() {
        return !driver.getCurrentUrl().equals(URL);
    }

    public boolean isLoginUnsuccessful() {
        return !errorMessage.doesExist();
    }

    public boolean badLoginSuccessful() throws InterruptedException {
        Thread.sleep(5000);
        return !errorMessage.doesExist();
    }

    public void validateSuccessfulLogin() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> loginSuccessful());
        } catch (TimeoutException e) {
            String message = errorMessage.getText();
            throw new PageValidationException("Login was not successful after 5 seconds: " + message);
        }
    }

    public void validateUnsuccessfulLogin() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> isLoginUnsuccessful());
        } catch (TimeoutException e) {
            String message = "Expected Unsuccessful Login, but no error message found after 5 seconds";
            throw new PageValidationException(message);
        }
    }

    public void signUpNewUser(User newUser) {
        // This is not a real feature
    }

    public void loginUnsuccessfully(User user) {
        login(user.getUsername(), user.getPassword());
        validateUnsuccessfulLogin();
    }

    public void loginSuccessfully(String un, String pw) {
        User user = new User();
        user.setUsername(un);
        user.setPassword(pw);
        loginSuccessfully(user);
    }
}
