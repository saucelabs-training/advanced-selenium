package com.saucelabs.advancedselenium.saucedemo.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucelabs.advancedselenium.saucedemo.pages.ElementValidationException;

import java.time.Duration;
import java.util.function.Function;

public class Element {
    private final By locator;
    private final WebDriverWait wait;
    private WebElement cachedElement;

    public Element(RemoteWebDriver driver, By locator) {
        this.locator = locator;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public boolean isDisplayed() {
        locate();
        return cachedElement.isDisplayed();
    }

    public void sendKeys(String value) {
        locate();
        run(() -> cachedElement.sendKeys(value), "send keys to element at: " + locator);
    }

    public void click() {
        locate();
        run(() -> cachedElement.click(), "click element at: " + locator);
    }

    private void locate() {
        this.cachedElement = (WebElement) wait.until((Function<WebDriver, Object>) d -> d.findElement(locator));
    }

    private void run(Runnable block, String message) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                block.run();
                break;
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                long currentTime = System.currentTimeMillis();
                Duration duration = Duration.ofMillis(currentTime - startTime);

                if (duration.compareTo(Duration.ofSeconds(20)) > 0) {
                    throw new ElementValidationException("Unable to " + message + " after " + duration + " seconds", e);
                }
            }
        }
    }
}
