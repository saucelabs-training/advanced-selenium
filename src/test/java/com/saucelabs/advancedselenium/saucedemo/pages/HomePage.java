package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

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
        this.driver = driver;
    }

    public void login(String username, String password) {
        driver.findElement(usernameTextfield).sendKeys(username);
        driver.findElement(passwordTextfield).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public boolean isLockedOut() {
        return driver.findElement(errorElement).getText().contains("Sorry, this user has been locked out");
    }

    public void validateLoggedIn() {
        HeaderSection headerSection = new HeaderSection(driver);
        if (!headerSection.isLoggedIn()) {
            List<WebElement> errors = driver.findElements(errorElement);
            String additional = errors.isEmpty() ? "" : " found error: " + errors.get(0).getText();
            throw new PageValidationException("User is not logged in;" + additional);
        }
    }
}
