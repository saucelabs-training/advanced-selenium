package test.java.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.exceptions.PageValidationException;

import java.util.List;
import java.util.function.Function;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    // Note, in production code I would set this higher
    protected static int defaultWaitTime = 5;
    private Boolean refreshToggle = false;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, defaultWaitTime);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public boolean doesElementExist(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public WebElement locateElement(String name, By locator) {
        StackTraceElement callingMethod = Thread.currentThread().getStackTrace()[2];

        return locateElement(name, locator, callingMethod);
    }

    public WebElement locateDisplayedElement(String name, By locator) {
        StackTraceElement callingMethod = Thread.currentThread().getStackTrace()[2];

        WebElement element = locateElement(name, locator, callingMethod);
        if (!element.isDisplayed()) {
            String msg = "'" + name + "' element in " + callingMethod.getClassName()
                    + " was located, but is not displayed;";
            throw new ElementNotInteractableException(msg);
        } else {
            return element;
        }
    }

    private WebElement locateElement(String name, By locator, StackTraceElement callingMethod) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            String msg = "Attempted to locate '" + name + "' element in " + callingMethod.getClassName() + ", but ";
            throw new NoSuchElementException(msg + e.getMessage());
        }
    }

    protected WebElement partialStringMatch(By locator, String string)  {
        try {
            wait.until((Function<WebDriver, Object>) driver -> doesElementExist(locator));
        } catch (TimeoutException e) {
            throw new PageValidationException("Unable to find any elements with the locator " + locator.toString());
        }

        List<WebElement> elements = driver.findElements(locator);

        for (WebElement element : elements){
            if(element.getText().contains(string)) {
                return element;
            }
        }
        throw new PageValidationException("Can not find an element matching "
                + string + " with the locator " + locator.toString());
    }

    protected void click(String description, By locator) {
        waitForExists(description, locator);
        WebElement element = driver.findElement(locator);
        waitForDisplayed(description, locator, element);

        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            click(description, locator);
        }
    }

    protected void sendKeys(String description, By locator, String text) {

    }

    private void waitForExists(String description, By locator) {
        try {
            wait.until((Function<WebDriver, Object>) driver -> doesElementExist(locator));
        } catch (TimeoutException e) {
            throw new PageValidationException("After " + defaultWaitTime
                    + " seconds, unable to find any elements matching "
                    + description + " with the locator " + locator.toString());
        }
    }

    private void waitForDisplayed(String description, By locator, WebElement element) {
        try {
            wait.until((Function<WebDriver, Object>) driver -> element.isDisplayed());
            // temporarily add this to show off potential race condition for Stale Element
            if (!refreshToggle) {
                refreshToggle = true;
                driver.navigate().refresh();
            }
        } catch (TimeoutException e) {
            throw new PageValidationException("Located Element " + description + ", but after"
                    + defaultWaitTime + " seconds, it was still not visible. Locator: "
                    + locator.toString());
        }
    }
}
