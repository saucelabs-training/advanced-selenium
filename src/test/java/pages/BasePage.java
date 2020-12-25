package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public boolean doesElementExist(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public WebElement locateElement(String name, By locator) {
        StackTraceElement callingMethod = Thread.currentThread().getStackTrace()[2];

        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            String msg = "Attempted to locate '" + name + "' element in " + callingMethod.getClassName() + ", but ";
            throw new NoSuchElementException(msg + e.getMessage());
        }
    }
}
