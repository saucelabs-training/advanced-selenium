package test.java.element;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.exceptions.PageValidationException;

import java.util.List;
import java.util.function.Function;

public class ElementCollection {
    private final String description;
    private WebDriver driver;
    private By locator;
    private List<WebElement> elements;
    protected static WebDriverWait wait;
    protected static int defaultWaitTime = 20;

    public ElementCollection(String description, By locator, WebDriver driver) {
        this.description = description;
        this.locator = locator;
        this.driver = driver;
        wait = new WebDriverWait(driver, defaultWaitTime);
    }

    private void locateElements() {
        wait.until((Function<WebDriver, Object>) driver -> driver.findElements(locator).size() > 0);
        this.elements = driver.findElements(locator);
    }

    public Element findMatchingSubstring(String matching) {
        if (elements == null) {
            locateElements();
        }
        for (WebElement element : elements){
            if(element.getText().contains(matching)) {
                return new Element(description, element, driver);
            }
        }
        throw new PageValidationException("Found " + elements.size() + " elements matching " + locator.toString()
                + ", but none of them matched " + matching);
    }
}
