package test.java.options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class InsecureCertificatesTest {
    WebDriver driver;

    @Test(expected = NoSuchElementException.class)
    public void withoutAccepting() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(false);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://expired.badssl.com/");
        driver.findElement(By.id("content"));
    }

    @Test
    public void withAccepting() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://expired.badssl.com/");
        driver.findElement(By.id("content"));
    }

    @Before
    public void manageDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
