package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.advancedselenium.saucedemo.data.User;

import java.util.List;
import java.util.function.Function;

public class HomePage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/";
    private final By usernameTextfield = By.cssSelector("input[data-test='username']");
    private final By passwordTextfield = By.cssSelector("input[data-test='password']");
    private final By loginButton = By.cssSelector("input[data-test='login-button']");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public static HomePage visit(RemoteWebDriver driver) {
        HomePage homePage = new HomePage(driver);
        driver.get(URL);
        return homePage;
    }

    public HomePage(RemoteWebDriver driver) {
        super(driver);
    }

    public void loginUnsuccessfully(User user) {
        login(user);

        try {
            wait.until((Function<WebDriver, Object>) driver -> !driver.findElements(errorElement).isEmpty());
        } catch (TimeoutException ex) {
            String url = driver.getCurrentUrl();
            throw new PageValidationException("Expected login errors, but none were found; current URL: " + url);
        }
    }

    public void loginSuccessfully(User user) {
        login(user);

        try {
            wait.until((Function<WebDriver, Object>) driver -> !URL.equals(driver.getCurrentUrl()));
        } catch (TimeoutException ex) {
            List<WebElement> errors = driver.findElements(errorElement);
            String additional = errors.isEmpty() ? "" : " found error: " + errors.get(0).getText();
            throw new PageValidationException("User is not logged in;" + additional);
        }
    }

    private void login(User user) {
        driver.findElement(usernameTextfield).sendKeys(user.getUsername());
        driver.findElement(passwordTextfield).sendKeys(user.getPassword());
        driver.findElement(loginButton).click();
    }
}
