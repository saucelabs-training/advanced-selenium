package com.saucelabs.advancedselenium.saucedemo;

import com.saucelabs.advancedselenium.saucedemo.pages.SauceDemoPageFactory;

public class SauceDemoApp {
    private final Browser browser;

    public SauceDemoApp(Browser browser) {
        this.browser = browser;
    }

    public Browser getBrowser() {
        return browser;
    }

    public SauceDemoPageFactory pages() {
        return new SauceDemoPageFactory(browser);
    }

    public void loginSuccessfully() {
        pages().getHomePage().visit().loginSuccessfully();
    }

    public void addItemToCart() {
        loginSuccessfully();
        pages().getInventoryPage().addItemSuccessfully();
    }
}
