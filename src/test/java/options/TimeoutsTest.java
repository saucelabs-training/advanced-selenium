package test.java.options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class TimeoutsTest {
    WebDriver driver;

    @Test
    public void script() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> timeouts = new HashMap<>();
        timeouts.put("script", 2000);
        chromeOptions.setCapability("timeouts", timeouts);

        driver = new ChromeDriver(chromeOptions);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long start = System.currentTimeMillis();
        try {
            js.executeAsyncScript("");
        } catch(ScriptTimeoutException e) {
            long timeElapsed = System.currentTimeMillis() - start;
            Assert.assertTrue(timeElapsed > 2000);
            Assert.assertTrue(timeElapsed < 30000);
        }
    }

    @Test
    public void implicit() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> timeouts = new HashMap<>();
        timeouts.put("implicit", 2000);
        chromeOptions.setCapability("timeouts", timeouts);

        driver = new ChromeDriver(chromeOptions);

        long start = System.currentTimeMillis();
        try {
            driver.findElement(By.id("not-there"));
        } catch(NoSuchElementException e) {
            long timeElapsed = System.currentTimeMillis() - start;
            Assert.assertTrue(timeElapsed > 2000);
            Assert.assertTrue(timeElapsed < 2200);
        }
    }

    @Test
    public void pageLoad() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> timeouts = new HashMap<>();
        timeouts.put("pageLoad", 1000);
        chromeOptions.setCapability("timeouts", timeouts);

        driver = new ChromeDriver(chromeOptions);

        long start = System.currentTimeMillis();
        try {
            driver.get("https://nytimes.com");
        } catch(TimeoutException e) {
            long timeElapsed = System.currentTimeMillis() - start;
            Assert.assertTrue(timeElapsed > 1000);
            Assert.assertTrue(timeElapsed < 1200);
        }
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
