package com.saucelabs.advancedselenium.saucedemo.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.pages.ElementValidationException;

import java.time.Duration;

public class Element {
    protected final By locator;
    protected final Browser browser;
    protected WebElement cachedElement;

    public Element(Browser browser, By locator) {
        this.browser = browser;
        this.locator = locator;
    }

    public boolean isDisplayed() {
        locate();
        return cachedElement.isDisplayed();
    }

    public void click() {
        run(() -> cachedElement.click(), "Unable to click element at: " + locator);
    }

    public void reset() {
        this.cachedElement = null;
    }

    public void waitForEnabled() {
        String message = "Element at " + locator + " never became enabled";
        run(() -> browser.waitUntil(() -> cachedElement.isEnabled()), message);
    }

    private void locate() {
        if (this.cachedElement == null) {
            this.cachedElement = (WebElement) browser.waitUntil(() -> browser.getDriver().findElement(locator));
        }
    }

    protected void run(Runnable block, String message) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                locate();
                block.run();
                break;
            } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException e) {
                reset();
                long currentTime = System.currentTimeMillis();
                Duration duration = Duration.ofMillis(currentTime - startTime);

                if (duration.compareTo(Duration.ofSeconds(20)) > 0) {
                    throw new ElementValidationException(message + " after " + duration + " seconds", e);
                }
            }
        }
    }
}
