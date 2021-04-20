package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class HomePage extends BasePage {
    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By submitButton = By.id("login-button");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/";
    }

    public void login(String user, String password) {
        driver.findElement(usernameTextField).sendKeys(user);
        driver.findElement(passwordTextField).sendKeys(password);
        driver.findElement(submitButton).click();
    }

    public String getError() {
        return driver.findElement(errorElement).getText();
    }

    public void validateLoginSuccessful() {
        if (isOnPage()) {
            throw new PageValidationException("Login was not successful: " + getError());
        }
    }
}
