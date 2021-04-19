package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.ElementValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.util.List;
import java.util.Random;

public class Element {
    private int maxRetries = 20;
    private final By locator;
    private final RemoteWebDriver driver;
    private final String description;
    private WebElement element;

    public Element(By locator, String description, BasePage page) {
        this.locator = locator;
        this.description = description;
        this.driver = page.getDriver();
    }

    public void click() {
        clickWithRetries(0);
    }

    public void sendKeys(String value) {
        sendKeysWithRetries(value, 0);
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

    private List<WebElement> locateAll() {
        return driver.findElements(locator);
    }

    private WebElement locateFirst() {
        if (element == null) {
            this.element = driver.findElement(locator);
        }
        return element;
    }

    private void clickWithRetries(int retries) {
        try {
            locateFirst().click();
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
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

    private void sendKeysWithRetries(String value, int retries) {
        try {
            locateFirst().sendKeys(value);
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
            if (retries++ > maxRetries) {
                throw new ElementValidationException("Unable to send keys to " + description + " after " + maxRetries + " attempts", ex);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendKeysWithRetries(value, retries);
        }
    }

    private String getTextWithRetries(int retries) {
        try {
            return locateFirst().getText();
        } catch (NoSuchElementException ex) {
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

