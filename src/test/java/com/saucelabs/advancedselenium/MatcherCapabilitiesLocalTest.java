package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MatcherCapabilitiesLocalTest {

    @Test
    public void browserName() {
        WebDriverManager.chromedriver().setup();

        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", "firefox");

        String chromeMessage = "session not created: No matching capabilities found";
        try {
            new ChromeDriver(capabilities).quit();
            fail();
        } catch (SessionNotCreatedException ex) {
            assertTrue(ex.getMessage().contains(chromeMessage));
        }
    }

    @Test
    public void browserVersionChrome() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "10");

        String chromeMessage = "session not created: No matching capabilities found";
        try {
            new ChromeDriver(options).quit();
            fail("https://bugs.chromium.org/p/chromedriver/issues/detail?id=3849");
        } catch (SessionNotCreatedException ex) {
            assertTrue(ex.getMessage().contains(chromeMessage));
        }
    }

    @Test
    public void browserVersionFirefox() {
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setCapability("browserVersion", "10");

        String firefoxMessage = "Unable to find a matching set of capabilities";
        try {
            new FirefoxDriver(options).quit();
            fail();
        } catch (SessionNotCreatedException ex) {
            assertTrue(ex.getMessage().contains(firefoxMessage));
        }
    }

    @Test
    public void platformName() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setCapability("platformName", "incorrect");

        String chromeMessage = "session not created: No matching capabilities found";
        try {
            new ChromeDriver(options).quit();
            fail();
        } catch (SessionNotCreatedException ex) {
            assertTrue(ex.getMessage().contains(chromeMessage));
        }
    }
}

