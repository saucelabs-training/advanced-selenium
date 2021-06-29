package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.openqa.selenium.OutputType.BYTES;

public class PageLoadStrategyTest {
    WebDriver driver;

    @Test
    public void none() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        driver = new FirefoxDriver(firefoxOptions);
        long elapsed = timedNavigation(driver, "https://www.nytimes.com/");

        saveScreenshot("PageLoadNone-" + elapsed + "ms.png");
    }

    @Test
    public void eager() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new FirefoxDriver(firefoxOptions);
        long elapsed = timedNavigation(driver, "https://www.nytimes.com/");

        saveScreenshot("PageLoadEager-" + elapsed + "ms.png");
    }

    @Test
    public void normal() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new FirefoxDriver(firefoxOptions);
        long elapsed = timedNavigation(driver, "https://www.nytimes.com/");

        saveScreenshot("PageLoadNormal-" + elapsed + "ms.png");
    }

    public void saveScreenshot(String path) {
        Path viewport = Paths.get(path);

        try {
            Files.write(viewport, ((TakesScreenshot) driver).getScreenshotAs(BYTES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long timedNavigation(WebDriver driver, String url) {
        long start = System.currentTimeMillis();
        driver.get(url);
        return System.currentTimeMillis() - start;
    }

    @Before
    public void manageDriver() {
        WebDriverManager.firefoxdriver().setup();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }
}
