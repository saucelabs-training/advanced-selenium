package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocalTest {

    @Test
    public void driverManagementOptions() {
        // Option 1
        // Download Driver & Add to PATH Environment Variable
        for (String a : System.getenv("PATH").split(":"))
            System.out.println(a);

        // Option 2
        // Download manually and explicitly specify location:
        String location = System.getProperty("user.home") + "/.webdrivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", location);

        // Option 3
        // Let WebDriverManager handle it for you
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.quit();
    }
}
