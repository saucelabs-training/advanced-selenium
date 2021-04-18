package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.ElementValidationException;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    public Duration defaultWaitTime = Duration.ofSeconds(20);
    private int maxRetries = 20;
    protected RemoteWebDriver driver;
    protected String pageUrl;
    protected WebDriverWait wait;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, defaultWaitTime);
    }

    public void visit() {
        driver.get(pageUrl);
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals(pageUrl);
    }

    public WebElement getElement(String locatorName) {
        try {
            Field field = this.getClass().getDeclaredField(locatorName);
            field.setAccessible(true);
            return driver.findElement((By) field.get(this));
        } catch (NoSuchElementException e) {
            String msg = "Attempted to locate '" + locatorName + "' element in " + this.getClass() + ", but ";
            throw new NoSuchElementException(msg + e.getMessage());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<WebElement> getElements(String locatorName) {
        try {
            Field field = this.getClass().getDeclaredField(locatorName);
            field.setAccessible(true);
            return driver.findElements((By) field.get(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void click(String locatorName) {
        clickWithRetries(locatorName, 0);
    }

    public void sendKeys(String locatorName, String value) {
        sendKeysWithRetries(locatorName, value, 0);
    }

    public boolean isElementPresent(String locatorName) {
        return !getElements(locatorName).isEmpty();
    }

    private void clickWithRetries(String locatorName, int retries) {
        try {
            getElement(locatorName).click();
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
            try {
                if (retries++ > maxRetries) {
                    throw new ElementValidationException("Unable to click after " + maxRetries + "attempts; ", ex);
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clickWithRetries(locatorName, retries);
        }
    }

    private void sendKeysWithRetries(String locatorName, String value, int retries) {
        try {
            getElement(locatorName).sendKeys(value);
        } catch (NoSuchElementException | ElementNotInteractableException ex) {
            try {
                if (retries++ > maxRetries) {
                    throw new ElementValidationException("Unable to send keys after " + maxRetries + "attempts; ", ex);
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendKeysWithRetries(locatorName, value, retries);
        }
    }
}
