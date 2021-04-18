package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.ElementValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class Element {
    private int maxRetries = 20;
    private final String locatorName;
    private final RemoteWebDriver driver;
    private final BasePage page;

    public Element(String locatorName, BasePage page) {
        this.locatorName = locatorName;
        this.driver = page.getDriver();
        this.page = page;
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
        return !locateAll().isEmpty();
    }

    public WebElement getRandom() {
        List<WebElement> all = locateAll();
        return all.get(new Random().nextInt(all.size()));
    }

    private List<WebElement> locateAll() {
        try {
            Field field = page.getClass().getDeclaredField(locatorName);
            field.setAccessible(true);
            return driver.findElements((By) field.get(page));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private WebElement locateFirst() {
        try {
            Field field = page.getClass().getDeclaredField(locatorName);
            field.setAccessible(true);
            return driver.findElement((By) field.get(page));
        } catch (NoSuchElementException e) {
            String msg = "Attempted to locate '" + locatorName + "' element in " + page.getClass() + ", but ";
            throw new NoSuchElementException(msg + e.getMessage());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void clickWithRetries(int retries) {
        try {
            locateFirst().click();
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
            if (retries++ > maxRetries) {
                throw new ElementValidationException("Unable to click; ", ex);
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
                throw new ElementValidationException("Unable to send keys; ", ex);
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
                throw new ElementValidationException("Unable to get text; ", ex);
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

