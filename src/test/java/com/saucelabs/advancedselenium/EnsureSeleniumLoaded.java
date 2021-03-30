package test.java.com.saucelabs.advancedselenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class EnsureSeleniumLoaded {
    public static void main(String[] args) {
        RemoteWebDriver driver = new ChromeDriver();
        driver.quit();
    }
}
