package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertEquals;

public class InsecureCertificatesTest {
    WebDriver driver;

    @Test
    public void accepts() {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);

        driver.get("https://expired.badssl.com/");
        assertEquals(1, driver.findElements(By.id("content")).size());
    }

    @Test
    public void doesNotAccept() {
        driver = new ChromeDriver();

        driver.get("https://expired.badssl.com/");
        assertEquals(0, driver.findElements(By.id("content")).size());
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
