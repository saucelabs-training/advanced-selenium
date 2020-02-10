package test.examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeleniumScript {

    @Test
    @DisplayName("Open Browser")
    void openBrowser() {

        // Set location of chromedriver
        if (System.getProperty("os.name").startsWith("Windows")) {
            System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", "lib/drivers/chromedriver");
        }

        // Start session (opens browser)
        WebDriver driver = new ChromeDriver();

        // Quit session (closes browser)
        driver.quit();
    }
}
