package test.java.options;

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
    public void none() throws IOException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        driver = new FirefoxDriver(firefoxOptions);

        long start = System.currentTimeMillis();
        driver.get("https://www.nytimes.com/");
        long timeElapsed = System.currentTimeMillis() - start;

        Path viewport = Paths.get("PageLoadNone-" + timeElapsed + "ms.png");
        Files.write(viewport, ((TakesScreenshot) driver).getScreenshotAs(BYTES));
    }

    @Test
    public void eager() throws IOException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new FirefoxDriver(firefoxOptions);

        long start = System.currentTimeMillis();
        driver.get("https://www.nytimes.com/");
        long timeElapsed = System.currentTimeMillis() - start;

        Path viewport = Paths.get("PageLoadEager-" + timeElapsed + "ms.png");
        Files.write(viewport, ((TakesScreenshot) driver).getScreenshotAs(BYTES));

    }

    @Test
    public void normal() throws IOException {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        driver = new FirefoxDriver(firefoxOptions);

        long start = System.currentTimeMillis();
        driver.get("https://www.nytimes.com/");
        long timeElapsed = System.currentTimeMillis() - start;

        Path viewport = Paths.get("PageLoadNormal-" + timeElapsed + "ms.png");
        Files.write(viewport, ((TakesScreenshot) driver).getScreenshotAs(BYTES));
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
