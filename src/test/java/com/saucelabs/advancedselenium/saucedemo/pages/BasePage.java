package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public abstract class BasePage {
    protected RemoteWebDriver driver;
    protected WebDriverWait wait;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void sendKeys(By locator, String value) {
        WebElement element = (WebElement) wait.until((Function<WebDriver, Object>) d -> d.findElement(locator));
        run(() -> element.sendKeys(value));
    }

    public void click(By locator) {
        WebElement element = (WebElement) wait.until((Function<WebDriver, Object>) d -> d.findElement(locator));
        run(element::click);
    }

    private void run(Runnable block) {
        long startTime = System.currentTimeMillis();
        while (true) {
            try {
                block.run();
                break;
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                long currentTime = System.currentTimeMillis();
                Duration duration = Duration.ofMillis(currentTime - startTime);

                if (duration.compareTo(Duration.ofSeconds(20)) > 0) {
                    throw new ElementValidationException("Unable to send keys after " + duration + " seconds", e);
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
}
