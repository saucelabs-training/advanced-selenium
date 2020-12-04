package test.java.options;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;

public class ChromeTest {
    WebDriver driver;

    @Test
    public void w3cAndChromeValues() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // w3c options
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.setAcceptInsecureCerts(false);
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        // chrome specific
        chromeOptions.addArguments("start-fullscreen");
        chromeOptions.setCapability("detach", true);

        driver = new ChromeDriver(chromeOptions);
    }


    // Note - "start-maximize" does not work on Mac

    @Test
    public void useArgsToStartFullscreen() {
        ChromeOptions chromeOptions = new ChromeOptions();
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
        chromeOptions.setBinary("/Applications/Chromium.app/Contents/MacOS/Chromium");
        // This is needed because Chromium 87 prompts for keychain credentials
        chromeOptions.addArguments("use-mock-keychain");

        driver = new ChromeDriver(chromeOptions);
        driver.get("chrome://settings/help");

        Assert.assertEquals("Settings - About Chromium", driver.getTitle());
    }

    @Test
    public void useExtensionsToStartWithExtension() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addExtensions(new File("assets/start-page.crx"));

        driver = new ChromeDriver(chromeOptions);
        switchWindow();

        String success = "Congratulations! Startpage is now your default search engine.";
        Assert.assertEquals(success, driver.getTitle());
    }

    @Test
    public void usePrefsToChangeDownloadDirectory() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();

        String path = Paths.get("").toAbsolutePath().toString();
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
    public void useExludeSwitchesToEnablePopupBlocking() {
        ChromeOptions chromeOptions = new ChromeOptions();
        // Chrome defaults to blocking popups
        // Chromedriver by default adds the switch to disable-popup-blocking
        // This excludes that automatically added switch
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

        // Don't assign variable and it won't call quit
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


    // The following are not currently working

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

    // These aren't working as expected

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



    // Note for future:
    // Sauce Sets these by default:
    //    "start-maximized",
    //    "disable-infobars",
    //    "ignore-gpu-blacklist",
    //    "test-type",
    //    "disable-gpu"


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
