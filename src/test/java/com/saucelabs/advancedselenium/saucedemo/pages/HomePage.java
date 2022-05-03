package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.data.User;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

public class HomePage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/";
    private final TextField username = browser.getTextField("username");
    private final TextField password = browser.getTextField("password");
    private final Button submit = browser.getButton("login-button");
    private final ElementList errorElements = browser.getElements("error");

    public HomePage visit() {
        browser.get(URL);
        return this;
    }

    public HomePage(SauceDemoApp app) {
        super(app);
    }

    public void loginUnsuccessfully(User user) {
        submitForm(user);

        try {
            errorElements.waitUntilPresent();
        } catch (TimeoutException ex) {
            String url = browser.getCurrentUrl();
            throw new PageValidationException("Expected login errors, but none were found; current URL: " + url);
        }
    }

    public void loginSuccessfully() {
        loginSuccessfully(User.valid());
    }

    public void loginSuccessfully(User user) {
        submitForm(user);

        try {
            browser.waitUntil(() -> !URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            String additional = errorElements.isEmpty() ? "" : " found error: " + errorElements.getFirst().getText();
            throw new PageValidationException("User is not logged in;" + additional);
        }
    }
}
