package com.saucelabs.advancedselenium.saucedemo.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.saucelabs.advancedselenium.saucedemo.Browser;

import java.util.List;
import java.util.Random;

public class ElementList {
    private final By locator;
    private final Browser browser;
    private List<WebElement> cachedElements;

    public ElementList(Browser browser, By locator) {
        this.locator = locator;
        this.browser = browser;
    }

    public void reset() {
        this.cachedElements = null;
    }

    public boolean isEmpty() {
        locateAll();
        return cachedElements.isEmpty();
    }

    public WebElement get(int i) {
        waitUntilPresent();
        return cachedElements.get(i);
    }

    public WebElement getFirst() {
        return get(0);
    }

    public WebElement getRandom() {
        waitUntilPresent();
        return cachedElements.get(new Random().nextInt(cachedElements.size()));
    }

    public void waitUntilPresent() {
        browser.waitUntil(() -> {
            reset();
            return !isEmpty();
        });
    }

    private void locateAll() {
        if (this.cachedElements == null) {
            this.cachedElements = browser.getDriver().findElements(locator);
        }
    }
}
