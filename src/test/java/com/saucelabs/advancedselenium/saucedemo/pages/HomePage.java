package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.elements.TextFieldElement;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.User;

import java.util.function.Function;

public class HomePage extends BasePage {
    private final TextFieldElement usernameTextField = getTextField(By.id("user-name"));
    private final TextFieldElement passwordTextField = getTextField(By.id("password"));
    private final ButtonElement submitButton = getButton(By.id("login-button"));
    private final Element errorElement = getElement(By.cssSelector("[data-test=error]"), "Error Eleemnt");

    public HomePage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/";
    }

    public void loginSuccessfully() {
        loginSuccessfully(User.valid());
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
            wait.until((Function<WebDriver, Object>) driver ->
                    errorElement.isElementPresent());
            if (getError().contains(msg)) {
                return;
            }
        } catch (TimeoutException ignored) {
        }
        throw new PageValidationException("Expected error with: " + msg
                + ", but none found; Current page is: " + driver.getCurrentUrl());
    }

    private void login(User user) {
        usernameTextField.sendKeys(user.getUser());
        passwordTextField.sendKeys(user.getPassword());
        submitButton.click();
    }

    public String getError() {
        return errorElement.getText();
    }
}
