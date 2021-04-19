package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.*;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.ElementValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class TextFieldElement extends Element {
    public TextFieldElement(By locator, BasePage page) {
        this(locator, "Text Field Element", page);
    }

    public TextFieldElement(By locator, String description, BasePage page) {
        super(locator, description, page);
    }

    public void sendKeys(String value) {
        sendKeysWithRetries(value, 0);
    }

    private void sendKeysWithRetries(String value, int retries) {
        try {
            WebElement webElement = locateFirst();
            webElement.clear();
            webElement.sendKeys(value);
        } catch (NoSuchElementException | ElementNotInteractableException | StaleElementReferenceException ex) {
            if (retries++ > maxRetries) {
                throw new ElementValidationException("Unable to send keys to " + description + " after " + maxRetries + " attempts", ex);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendKeysWithRetries(value, retries);
        }
    }
}
