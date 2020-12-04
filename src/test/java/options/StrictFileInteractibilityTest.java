package test.java.options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

// This is not currently working on Selenium 3.141.59
// Passing in true does not seem to have an effect
public class StrictFileInteractibilityTest {
    WebDriver driver;

    @Test
    public void withStrictFileInteractibility() throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("strictFileInteractability", true);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/upload");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"file-upload\").setAttribute(\"style\", \"visibility: hidden\");");
        File file = File.createTempFile("temp-file", null);

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("file-submit")).click();
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), file.getName());
    }

    // Default Behavior
    @Test
    public void withoutStrictFileInteractibility() throws IOException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("strictFileInteractability", false);

        driver = new ChromeDriver(chromeOptions);
        driver.get("https://the-internet.herokuapp.com/upload");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"file-upload\").setAttribute(\"style\", \"visibility: hidden\");");
        File file = File.createTempFile("temp-file", null);

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("file-submit")).click();
        Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), file.getName());
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
