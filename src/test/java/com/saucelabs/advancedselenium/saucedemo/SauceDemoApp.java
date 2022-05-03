package com.saucelabs.advancedselenium.saucedemo;

import com.saucelabs.advancedselenium.saucedemo.pages.HeaderSection;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
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

    public void loginSuccessfully() {
        pages().getHomePage().visit().loginSuccessfully();
    }

    public void addItemToCart() {
        loginSuccessfully();
        pages().getInventoryPage().addItemSuccessfully();
    }

    public Integer getNumberItemsInCart() {
        HeaderSection headerSection = new HeaderSection(this);
        return headerSection.getNumberItemsInCart();
    }

    public Boolean isAuthenticated() {
        new InventoryPage(this).visit();
        return InventoryPage.URL.equals(browser.getCurrentUrl());
    }
}
