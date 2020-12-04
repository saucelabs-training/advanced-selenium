package test.java;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteTest {

    @Test
    public void openBrowser() {
        // Start Selenium Server on localhost
        // java -jar selenium-server-standalone-3.141.59.jar
        ChromeOptions chromeOptions = new ChromeOptions();
        RemoteWebDriver driver = new RemoteWebDriver(chromeOptions);

        driver.quit();
    }
}

