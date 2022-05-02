package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
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
        wait.until((Function<WebDriver, Object>) d -> element.isDisplayed());
        wait.until((Function<WebDriver, Object>) d -> element.isEnabled());
        element.sendKeys(value);
    }
}
