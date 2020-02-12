package test.base;

import test.extensions.SauceTestWatcher;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SauceBase {
    protected WebDriver driver;

    @RegisterExtension
    static SauceTestWatcher testWatcher = new SauceTestWatcher();

    private static String username = "yourusername";
    private static String accessKey = "00000000-0000-0000-0000-000000000000";

    @BeforeEach
    public void setup(TestInfo testInfo) throws MalformedURLException {
        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability("name", testInfo.getDisplayName());
        sauceOptions.setCapability("build", "Sauce Day Charlotte Example: 1");
        sauceOptions.setCapability("extendedDebugging", true);
        sauceOptions.setCapability("capturePerformance", true);

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setCapability("platformName", "Windows 10");
        browserOptions.setCapability("browserVersion", "80.0");
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://" + getUsername() + ":" + getAccessKey() + "@ondemand.saucelabs.com/wd/hub");

        driver = new RemoteWebDriver(url, browserOptions);

        testWatcher.setDriver(driver);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    private static String getUsername() {
        if (System.getenv("SAUCE_USERNAME") != null) {
            return System.getenv("SAUCE_USERNAME");
        } else {
            return username;
        }
    }

    private static String getAccessKey() {
        if (System.getenv("SAUCE_ACCESS_KEY") != null) {
            return System.getenv("SAUCE_ACCESS_KEY");
        } else {
            return accessKey;
        }
    }
}
