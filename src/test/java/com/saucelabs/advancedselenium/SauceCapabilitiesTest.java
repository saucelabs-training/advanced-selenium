package com.saucelabs.advancedselenium;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SauceCapabilitiesTest {

    @Rule
    public TestName testName = new TestName();

    @Test
    public void mixOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches",
                Collections.singletonList("disable-popup-blocking"));

        SauceOptions options = SauceOptions.chrome(chromeOptions)
                // w3c Matchers
                .setPlatformName(SaucePlatform.MAC_CATALINA)
                // w3c Setters
                .setAcceptInsecureCerts()
                .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                // Sauce Setters:
                .setName(testName.getMethodName())
                .setScreenResolution("1152x864")
                .setIdleTimeout(Duration.ofSeconds(30))
                .build();
        SauceSession session = new SauceSession(options);
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
    }

    @Test
    public void saucePlatformOptions() {
        SauceOptions sauceOptions = SauceOptions.firefox()
                .setPlatformName(SaucePlatform.MAC_MOJAVE)
                .setBrowserVersion("88.0")
                .setTimeZone("Alaska")
                .setScreenResolution("1152x864")
                .setGeckodriverVersion("0.29.1")
                .setSeleniumVersion("3.141.5")
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
    }

    @Test
    public void sauceTestInformation() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("foo");
        tags.add("bar");

        Map<String, Object> customData = new HashMap<>();
        customData.put("foo", "bar");
        customData.put("bar", "foo");

        SauceOptions sauceOptions = SauceOptions.chrome()
                .setName(testName.getMethodName())
                .setBuild("Sauce Test Info Demo: " + System.currentTimeMillis())
                .setTags(tags)
                .setCustomData(customData)
                .setJobVisibility(JobVisibility.PUBLIC)
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
    }

    @Test
    public void sauceArtifactsOptions() {
        SauceOptions sauceOptions = SauceOptions.chrome()
                .disableRecordVideo()
                .disableRecordLogs()
                .disableRecordScreenshots()
                .disableVideoUploadOnPass()
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
    }

    @Test
    public void sauceBehaviorOptions() {
        SauceOptions sauceOptions = SauceOptions.chrome()
                .setName(testName.getMethodName())
                .setPriority(1)
                .setMaxDuration(Duration.ofMinutes(10))
                .setIdleTimeout(Duration.ofSeconds(30))
                .setCommandTimeout(Duration.ofMinutes(1))
                .setExtendedDebugging()
                .setCapturePerformance()
                .build();

        SauceSession session = new SauceSession(sauceOptions);
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
    }
}
