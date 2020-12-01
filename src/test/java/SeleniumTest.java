package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    @Test
    public void openBrowser() {
        // Let WebDriverManager handle drivers
        WebDriverManager.chromedriver().setup();

        // Start session (opens browser)
        WebDriver driver = new ChromeDriver();

        // Quit session (closes browser)
        driver.quit();
    }
}
