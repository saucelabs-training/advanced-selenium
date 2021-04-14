package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    public Duration defaultWaitTime = Duration.ofSeconds(20);
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

    public boolean isElementPresent(String locatorName) {
        return !getElements(locatorName).isEmpty();
    }
}
