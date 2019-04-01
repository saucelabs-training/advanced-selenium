package test.pages;

import test.elements.Element;
import org.openqa.selenium.*;

class BasePage {
    WebDriver driver;

    Element getElement(By locator) {
        return new Element(driver, locator);
    }
}
