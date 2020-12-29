package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.element.Element;
import test.java.element.ElementCollection;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final int DEFAULT_WAIT_TIME = 20;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public Element getElement(String description, By locator) {
        return new Element(description, locator, driver);
    }

    public ElementCollection getElements(String description, By locator) {
        return new ElementCollection(description, locator, driver);
    }
}

