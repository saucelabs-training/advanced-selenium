package com.saucelabs.advancedselenium.saucedemo.elements;

import org.openqa.selenium.By;
import com.saucelabs.advancedselenium.saucedemo.Browser;

public class TextField extends Element {
    public TextField(Browser browser, By locator) {
        super(browser, locator);
    }

    public void sendKeys(String value) {
        run(() -> cachedElement.clear(), "Unable to clear element at: " + locator);
        run(() -> cachedElement.sendKeys(value), "Unable to send keys to element at: " + locator);
    }
}
