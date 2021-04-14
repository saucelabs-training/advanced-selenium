package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;

import java.lang.reflect.Field;
import java.util.List;

public abstract class BasePage {
    protected RemoteWebDriver driver;
    protected String pageUrl;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void visit() {
        driver.get(pageUrl);
    }

    public boolean isOnPage() {
        if (pageUrl == null) {
            throw new PageValidationException("Can not evaluate if on a given page if pageUrl is not defined");
        }
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
}
