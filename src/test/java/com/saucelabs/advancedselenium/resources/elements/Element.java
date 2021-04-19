package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.ElementValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class Element {
    protected int maxRetries = 20;
    public Duration defaultWaitTime = Duration.ofSeconds(20);
    private final By locator;
    private final RemoteWebDriver driver;
    protected final String description;
    protected WebDriverWait wait;
    private WebElement element;

    public Element(By locator, String description, BasePage page) {
        this.locator = locator;
        this.description = description;
        this.driver = page.getDriver();
        this.wait = new WebDriverWait(driver, defaultWaitTime);
    }

    public void click() {
        clickWithRetries(0);
    }

    public String getText() {
        return getTextWithRetries(0);
    }

    public boolean isElementPresent() {
        try {
            locateFirst();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public WebElement getRandom() {
        List<WebElement> all = locateAll();
        return all.get(new Random().nextInt(all.size()));
    }

    public String toString() {
        return description + "located by: " + locator.toString();
    }

    protected void waitForEnabled() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> locateFirst().isEnabled());
        } catch (NoSuchElementException ignored) {
            waitForExists();
            waitForEnabled();
        } catch (TimeoutException ex) {
            throw new ElementValidationException("Located Element " + toString() + ", but after"
                    + defaultWaitTime + " seconds, it was still not enabled.");
        }
    }

    protected void waitForExists() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> !locateAll().isEmpty());
        } catch (TimeoutException ex) {
            throw new ElementValidationException("Unable to locate " + toString()
                    + " after " + defaultWaitTime + " seconds");
        }
    }

    private List<WebElement> locateAll() {
        return driver.findElements(locator);
    }

    protected WebElement locateFirst() {
        if (element == null) {
            this.element = driver.findElement(locator);
        }
        return element;
    }

    private void clickWithRetries(int retries) {
        try {
            locateFirst().click();
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException ex) {
            if (retries++ > maxRetries) {
                throw new ElementValidationException("Unable to click " + description + " after " + maxRetries + " attempts", ex);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clickWithRetries(retries);
        }
    }

    private String getTextWithRetries(int retries) {
        try {
            return locateFirst().getText();
        } catch (NoSuchElementException | StaleElementReferenceException ex) {
            if (retries++ > maxRetries) {
                throw new ElementValidationException("Unable to get text of " + description + " after " + maxRetries + " attempts", ex);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return getTextWithRetries(retries);
        }
    }
}
