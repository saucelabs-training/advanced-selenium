package test.java.options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserVersionTest {
    WebDriver driver;

    @Ignore("This is a bug and needs to be reported")
    @Test(expected = SessionNotCreatedException.class)
    public void chromeRaisesException() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "10");

        driver = new ChromeDriver(chromeOptions);
    }

    @Test(expected = SessionNotCreatedException.class)
    public void firefoxRaisesException() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("browserVersion", "10");

        driver = new FirefoxDriver(firefoxOptions);
    }


    @Before
    public void manageDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
