package test.java.element;

import org.openqa.selenium.*;
import test.java.exceptions.PageValidationException;

import java.time.Instant;

import static java.util.concurrent.TimeUnit.SECONDS;

public class TextElement extends Element {

    public TextElement(String description, By locator, WebDriver driver) {
        super(description, locator, driver);
    }

    public void clear() {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(DEFAULT_WAIT_TIME);

        try {
            locateElement().clear();
        } catch (StaleElementReferenceException | NotFoundException | InvalidElementStateException e) {
            if (Instant.now().toEpochMilli() > expireTime) {
                String message = "After attempting for " + DEFAULT_WAIT_TIME + " seconds, " + e.getMessage();
                throw new PageValidationException(message);
            } else {
                element = null;
                clear();
            }
        }
    }

    public void sendKeys(String string) {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(DEFAULT_WAIT_TIME);

        try {
            locateElement().sendKeys(string);
        } catch (StaleElementReferenceException | NotFoundException | InvalidElementStateException e) {
            if (Instant.now().toEpochMilli() > expireTime) {
                String message = "After attempting for " + DEFAULT_WAIT_TIME + " seconds, " + e.getMessage();
                throw new PageValidationException(message);
            } else {
                element = null;
                sendKeys(string);
            }
        }
    }
}
