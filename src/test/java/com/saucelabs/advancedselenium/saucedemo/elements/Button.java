package com.saucelabs.advancedselenium.saucedemo.elements;

import org.openqa.selenium.By;
import com.saucelabs.advancedselenium.saucedemo.Browser;

public class Button extends Element {
    public Button(Browser browser, By locator) {
        super(browser, locator);
    }

    @Override
    public void click() {
        waitForEnabled();
        super.click();
    }
}
