package test.java.element;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NestedElement extends Element {
    private Element scopeElement;

    public NestedElement(String description, By locator, Element element, WebDriver driver) {
        super(description, locator, driver);
        this.scopeElement = element;
    }

    protected WebElement locateElement() {
        waitForExists();
        this.element = scopeElement.getElement().findElement(locator);
        waitForDisplayed();
        return element;
    }

    public Boolean doesExist() {
        try {
            scopeElement.getElement().findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

}
