package test.java.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.exceptions.PageValidationException;

import java.util.List;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    // Note, in production code I would set this higher
    protected static int defaultWaitTime = 5;

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
        List<WebElement> elements = driver.findElements(locator);

        if (elements.size() == 0) {
            throw new PageValidationException("Unable to find any elements with the locator " + locator.toString());
        }

        for (WebElement element : elements){
            if(element.getText().contains(string)) {
                return element;
            }
        }
        throw new PageValidationException("Can not find an element matching "
                + string + " with the locator " + locator.toString());
    }
}
