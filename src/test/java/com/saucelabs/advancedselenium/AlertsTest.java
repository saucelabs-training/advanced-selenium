package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class AlertsTest {
    WebDriver driver;
    ChromeOptions chromeOptions = new ChromeOptions();
    String result;

    @Test
    public void accept() {
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElements(By.tagName("button")).get(1).click();

        // It's like it was never there, except it was
        result = driver.findElement(By.id("result")).getText();
        assertEquals("You clicked: Ok", result);
    }

    @Test
    public void dismiss() {
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElements(By.tagName("button")).get(1).click();

        // It's like it was never there, except it was
        result = driver.findElement(By.id("result")).getText();
        assertEquals("You clicked: Cancel", result);
    }

    @Test
    public void acceptAndNotify() {
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT_AND_NOTIFY);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElements(By.tagName("button")).get(1).click();

        try {
            // This will accept alert
            result = driver.findElement(By.id("result")).getText();
            fail("This should to throw an exception");
        } catch (UnhandledAlertException ex) {
            // exception thrown instead of getting Text
            assertNull(result);
        }

        result = driver.findElement(By.id("result")).getText();
        assertEquals("You clicked: Ok", result);
    }

    // Default Behavior
    @Test
    public void dismissAndNotify() {
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElements(By.tagName("button")).get(1).click();

        try {
            // This will accept alert
            result = driver.findElement(By.id("result")).getText();
            fail("This should to throw an exception");
        } catch (UnhandledAlertException ex) {
            // exception thrown instead of getting Text
            assertNull(result);
        }

        result = driver.findElement(By.id("result")).getText();
        assertEquals("You clicked: Cancel", result);
    }

    @Test
    public void ignore() {
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        driver.findElements(By.tagName("button")).get(1).click();

        try {
            driver.getTitle();
            fail("This should to throw an exception");
        } catch (UnhandledAlertException ignored) {
        }

        try {
            driver.getTitle();
            fail("This should still throw an exception");
        } catch (UnhandledAlertException ignored) {
        }

        try {
            driver.getTitle();
            fail("Yup, still throws an exception");
        } catch (UnhandledAlertException ignored) {
        }

        driver.switchTo().alert().accept();
        result = driver.findElement(By.id("result")).getText();
        assertEquals("You clicked: Ok", result);
    }

    @Before
    public void manageDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
