package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
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

    public void loginSuccessfully(String user, String password) {
        login(user, password);
        validateLoginSuccessful();
    }

    public void loginLockedOutUserUnsuccessfully(String user, String password) {
        loginUnsuccessfully(user, password, "Sorry, this user has been locked out");
    }

    public String loginUnsuccessfully(String user, String password, String msg) {
        try {
            loginSuccessfully(user, password);
        } catch (PageValidationException ex) {
            if (ex.getMessage().contains(msg)) {
                return null;
            }
        }
        String currentUrl = driver.getCurrentUrl();
        throw new PageValidationException("Expected error with: " + msg
                + ", but none found; Current page is: " + currentUrl);
    }

    private void login(String user, String password) {
        getElement("usernameTextField").sendKeys(user);
        getElement("passwordTextField").sendKeys(password);
        getElement("submitButton").click();
    }

    public String getError() {
        return getElement("errorElement").getText();
    }

    public void validateLoginSuccessful() {
        if (isOnPage()) {
            throw new PageValidationException("Login was not successful: " + getError());
        }
    }

}
