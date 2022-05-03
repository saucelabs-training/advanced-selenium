package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;

public class HeaderSection extends BasePage {
    private final Button menuButton = browser.getButton(By.id("react-burger-menu-btn"));
    private final Element logoutLink = browser.getElement(By.id("logout_sidebar_link"));

    public HeaderSection(SauceDemoApp app) {
        super(app);
    }

    public void logOutSuccessfully() {
        menuButton.click();
        logoutLink.click();

        try {
            browser.waitUntil(() -> !app.isAuthenticated());
        } catch (TimeoutException ex) {
            throw new PageValidationException("User is still logged in;");
        }
    }
}
