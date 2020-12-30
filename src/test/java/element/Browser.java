package test.java.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Browser {
    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Element element(String description, By locator) {
        return new Element(description, locator, driver);
    }

    public ButtonElement button(String description, By locator) {
        return new ButtonElement(description, locator, driver);
    }

    public TextElement textField(String description, By locator) {
        return new TextElement(description, locator, driver);
    }

    public ElementCollection elements(String description, By locator) {
        return new ElementCollection(description, locator, driver);
    }

    public void quit() {
        driver.quit();
    }
}
