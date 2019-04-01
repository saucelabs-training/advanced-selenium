package test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class BasePage {
    WebDriver driver;

    void click(By locator) {
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

    private void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    private void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
