package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.User;

import java.util.function.Function;

public class HomePage extends BasePage {
    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By submitButton = By.id("login-button");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/";
    }

    public void loginSuccessfully(User user) {
        login(user);
        try {
            wait.until((Function<WebDriver, Object>) driver -> !isOnPage());
        } catch (TimeoutException ex) {
            throw new PageValidationException("Login was not successful: " + getError());
        }
    }

    public void loginLockedOutUserUnsuccessfully(User user) {
        loginUnsuccessfully(user, "Sorry, this user has been locked out");
    }

    public void loginUnsuccessfully(User user, String msg) {
        login(user);
        try {
            wait.until((Function<WebDriver, Object>) driver -> isElementPresent("errorElement"));
            if (getError().contains(msg)) {
                return;
            }
        } catch (TimeoutException ignored) {
        }
        throw new PageValidationException("Expected error with: " + msg
                + ", but none found; Current page is: " + driver.getCurrentUrl());
    }

    private void login(User user) {
        getElement("usernameTextField").sendKeys(user.getUser());
        getElement("passwordTextField").sendKeys(user.getPassword());
        getElement("submitButton").click();
    }

    public String getError() {
        return getElement("errorElement").getText();
    }
}
