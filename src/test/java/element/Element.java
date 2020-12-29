package test.java.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class Element {
    protected String description;
    private WebDriver driver;
    protected By locator;
    protected WebElement element;
    protected WebDriverWait wait;
    protected static int DEFAULT_WAIT_TIME = 20;

    public Element(String description, By locator, WebDriver driver) {
        this.description = description;
        this.locator = locator;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    public Element(String description, WebElement element, WebDriver driver) {
        this.description = description;
        this.driver = driver;
        this.element = element;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    public String getText() {
        return locateElement().getText();
    }

    public String getAttribute(String attribute) {
        return locateElement().getAttribute(attribute);
    }

    public void click() {
        try {
            locateElement().click();
        } catch (StaleElementReferenceException e) {
            click();
        }
    }

    public void clear() {
        try {
            locateElement().clear();
        } catch (StaleElementReferenceException e) {
            clear();
        }
    }

    public void sendKeys(String string) {
        try {
            locateElement().sendKeys(string);
        } catch (StaleElementReferenceException e) {
            sendKeys(string);
        }
    }

    protected WebElement locateElement() {
        waitForExists();
        element = driver.findElement(locator);
        waitForDisplayed();
        return element;
    }

    protected void waitForExists() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> doesExist());
        } catch (TimeoutException e) {
            throw new PageValidationException("After " + DEFAULT_WAIT_TIME
                    + " seconds, unable to find any elements matching "
                    + description + " with the locator " + locator.toString());
        }
    }

    protected void waitForDisplayed() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> element.isDisplayed());
        } catch (TimeoutException e) {
            throw new PageValidationException("Located Element " + description + ", but after"
                    + DEFAULT_WAIT_TIME + " seconds, it was still not visible. Locator: "
                    + locator.toString());
        }
    }

    public Boolean doesExist() {
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public WebElement getElement() {
        return this.element;
    }

    public NestedElement findElement(String description, By locator) {
        if (element == null) {
            locateElement();
        }

        return new NestedElement(description, locator, this, driver);
    }
}
