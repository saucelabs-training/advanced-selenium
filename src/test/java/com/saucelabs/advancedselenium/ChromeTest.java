package com.saucelabs.advancedselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

public class ChromeTest {
    WebDriver driver;

    @Test
    public void useArgsToStartFullscreen() {
        ChromeOptions chromeOptions = new ChromeOptions();
        // Note - "start-maximize" does not work on Mac
        chromeOptions.addArguments("start-fullscreen");

        driver = new ChromeDriver(chromeOptions);
        Dimension size = driver.manage().window().getSize();
        driver.manage().window().fullscreen();
        Dimension size2 = driver.manage().window().getSize();

        Assert.assertEquals(size, size2);
    }

    @Test
    public void useBinaryToSpecifyChromeLocation() {
        ChromeOptions chromeOptions = new ChromeOptions();
        String beta = "/Applications/Google Chrome Beta.app/Contents/MacOS/Google Chrome Beta";
        chromeOptions.setBinary(beta);

        driver = new ChromeDriver(chromeOptions);
        driver.get("chrome://version");
        WebElement version = driver.findElement(By.id("version"));

        Assert.assertTrue(version.getText().contains("beta"));
    }

    @Test
    public void useExtensionsToStartWithExtension() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("src/test/resources/start-page.crx"));

        driver = new ChromeDriver(chromeOptions);
        switchWindow();

        String success = "Congratulations! Startpage is now your default search engine.";
        Assert.assertEquals(success, driver.getTitle());
    }

    @Test
    public void usePrefsToChangeDownloadDirectory() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();

        String path = Paths.get("src/test/downloads").toAbsolutePath().toString();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", path);
        chromeOptions.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(chromeOptions);
        String fileName = uploadTempFile();

        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.partialLinkText(fileName)).click();

        Thread.sleep(2000);

        Assert.assertTrue(new File(path + "/" + fileName).exists());
    }

    @Test
    public void useExcludeSwitchesToEnablePopupBlocking() {
        ChromeOptions chromeOptions = new ChromeOptions();
        // Chrome defaults to blocking popups
        // Chromedriver by default adds the argument to disable-popup-blocking
        // This excludes that default added switch
        chromeOptions.setExperimentalOption("excludeSwitches",
                Collections.singletonList("disable-popup-blocking"));

        driver = new ChromeDriver(chromeOptions);
        driver.get("http://www.popuptest.com/popuptest1.html");

        Assert.assertEquals(1, driver.getWindowHandles().size());
    }

    @Test
    public void useDetachToDecoupleBrowserFromDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("detach", true);

        // Don't assign variable so after hook won't call quit
        new ChromeDriver(chromeOptions);
    }

    @Test
    public void useMobileEmulatorToChangeUserAgent() {
        ChromeOptions chromeOptions = new ChromeOptions();

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Pixel 2");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = new ChromeDriver(chromeOptions);

        driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent");
        String detected_value = driver.findElement(By.id("detected_value")).getText();

        Assert.assertTrue(detected_value.contains("Pixel 2"));
    }

    @Ignore("Chrome is not accepting debuggerAddress")
    @Test
    public void executeOnExistingBrowserOne() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-debugging-port=1234");

        RemoteWebDriver wd = new ChromeDriver(chromeOptions);
        wd.get("https://www.saucedemo.com");

        ChromeOptions chromeOptions2 = new ChromeOptions();
        chromeOptions2.setCapability("debuggerAddress", "localhost:61234");

        driver = new ChromeDriver(chromeOptions2);
        Assert.assertEquals("Swag Labs", driver.getTitle());
    }

    @Ignore("Remote Only? New Endpoint for Selenium 4?")
    @Test
    public void performanceLogging() {
        ChromeOptions chromeOptions = new ChromeOptions();

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable("performance", Level.ALL);

        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        Map<String, Object> perfLogPrefs = new HashMap<String, Object>();
        perfLogPrefs.put("traceCategories", "browser,devtools.timeline,devtools");

        chromeOptions.setExperimentalOption("perfLoggingPrefs", perfLogPrefs);

        driver = new ChromeDriver(chromeOptions);

        driver.get("https://nytimes.com");
    }

    @Ignore("org.openqa.selenium.WebDriverException: unknown error: cannot create default profile directory")
    @Test
    public void specifyProfile() throws IOException {
        driver = new ChromeDriver();

        String chrome = ((HasCapabilities) driver).getCapabilities().getCapability("chrome").toString();

        File fromPath = new File(chrome.substring(chrome.indexOf("userDataDir=") + 12, (chrome.length() - 1)));

        File toPath = new File(Paths.get("assets/profile2").toAbsolutePath().toString());
        FileFilter fileFilter = file -> {
            String name = file.getName();
            return !name.contains("RunningChromeVersion")
                    && !name.contains("Singleton");
        };

        FileUtils.copyDirectory(fromPath, toPath, fileFilter);

        driver.quit();

        ChromeOptions chromeOptions = new ChromeOptions();
        String argument = "user-data-dir=/assets/profile2";
        chromeOptions.addArguments(argument);

        driver = new ChromeDriver(chromeOptions);

        Assert.assertEquals("https://www.selenium.dev/", driver.getCurrentUrl());
    }

    // Helper Methods

    public void switchWindow() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(driver.getWindowHandle());
        String handle = windowHandles.iterator().next();
        driver.switchTo().window(handle);
    }

    private String uploadTempFile() {
        driver.get("https://the-internet.herokuapp.com/upload");
        File file = null;
        try {
            file = File.createTempFile("file", null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebElement upload = driver.findElement(By.id("file-upload"));
        upload.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("file-submit")).click();
        return file.getName();
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
