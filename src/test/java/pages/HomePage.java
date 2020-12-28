package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.data.User;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class HomePage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test=error]");

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
        login(user.getUsername(), user.getPassword());
        validateSuccessfulLogin();
    }

    public void loginSuccessfully(String username, String password) {
        login(username, password);
        validateSuccessfulLogin();
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(SUBMIT).click();
    }

    public boolean loginSuccessful() {
        return !driver.getCurrentUrl().equals(URL);
    }

    public boolean isLoginUnsuccessful() {
        return !doesElementExist(ERROR);
    }

    public boolean badLoginSuccessful() throws InterruptedException {
        Thread.sleep(5000);
        return !doesElementExist(ERROR);
    }

    public void validateSuccessfulLogin() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> loginSuccessful());
        } catch (TimeoutException e) {
            String message = driver.findElement(ERROR).getText();
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
}
