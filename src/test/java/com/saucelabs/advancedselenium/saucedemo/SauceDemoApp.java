package com.saucelabs.advancedselenium.saucedemo;

import com.saucelabs.advancedselenium.saucedemo.apis.SauceDemoAPIFactory;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
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
        return new SauceDemoPageFactory(this);
    }

    public SauceDemoAPIFactory apis() {
        return new SauceDemoAPIFactory(browser);
    }

    public void loginSuccessfully() {
        pages().getHomePage().visit();
        browser.waitUntil(() -> HomePage.URL.equals(browser.getCurrentUrl()));
        apis().getAuthenticateAPI().authenticate();
        pages().getInventoryPage().visit();
    }

    public void addItemToCart() {
        loginSuccessfully();
        apis().getInventoryAPI().addItemSuccessfully();
    }

    public Integer getNumberItemsInCart() {
        return apis().getCartAPI().getItemCount();
    }

    public Boolean isAuthenticated() {
        return apis().getAuthenticateAPI().isAuthenticated();
    }
}
