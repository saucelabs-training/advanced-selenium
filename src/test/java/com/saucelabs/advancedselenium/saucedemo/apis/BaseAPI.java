package com.saucelabs.advancedselenium.saucedemo.apis;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import com.saucelabs.advancedselenium.saucedemo.Browser;

public class BaseAPI {
    protected final Browser browser;

    public BaseAPI(Browser browser) {
        this.browser = browser;
    }

    protected void addCookie(String name, String value) {
        browser.addCookie(name, value);
    }

    protected Cookie getCookie(String name) {
        return browser.getCookie(name);
    }

    protected LocalStorage getLocalStorage() {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(browser.getDriver());
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        return webStorage.getLocalStorage();
    }
}
