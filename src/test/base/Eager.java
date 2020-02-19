package test.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import test.extensions.StdoutTestWatcher;

@ExtendWith(StdoutTestWatcher.class)
public class Eager {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("wdm.targetPath", "lib/drivers/auto/");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }
}
