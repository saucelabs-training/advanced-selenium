package com.saucelabs.advancedselenium.saucedemo.pages;


import com.saucelabs.advancedselenium.saucedemo.Browser;

public abstract class BasePage {
    protected Browser browser;

    public BasePage(Browser browser) {
        this.browser = browser;
    }
}
