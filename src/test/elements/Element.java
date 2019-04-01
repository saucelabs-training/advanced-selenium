package test.elements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {
    private WebDriver driver;
    private By locator;

    public Element(WebDriver driver, By locator) {
        this.driver = driver;
        this.locator = locator;
    }

    public void click() {
        while (true) {
            try {
                driver.findElement(locator).click();
                return;
            } catch (NoSuchElementException e) {
                waitForElement(locator);
            } catch (ElementNotInteractableException e) {
                waitForElementVisible(locator);
            }
        }
    }

    public void sendKeys(String string) {
        while (true) {
            try {
                driver.findElement(locator).sendKeys(string);
                return;
            } catch (NoSuchElementException e) {
                waitForElement(locator);
            } catch (ElementNotInteractableException e) {
                waitForElementVisible(locator);
            }
        }
    }

    private void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
