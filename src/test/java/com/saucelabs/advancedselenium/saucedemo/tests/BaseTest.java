package com.saucelabs.advancedselenium.saucedemo.tests;

import com.github.javafaker.shaded.snakeyaml.Yaml;
import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
import com.saucelabs.saucebindings.options.SauceOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

public class BaseTest {
    protected SauceDemoApp sauceDemoApp;
    protected SauceSession session;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeAll
    public static void toggleExecution() {
        // These would normally be toggled via CI tool ENV or similar
        System.setProperty("SELENIUM_PLATFORM", "SAUCE");
        System.setProperty("SAUCE_PLATFORM", "DEFAULT");
    }

    @BeforeEach
    public void setUp(TestInfo testinfo) {
        RemoteWebDriver driver;
        if ("LOCAL".equals(System.getProperty("SELENIUM_PLATFORM"))) {
            driver = runLocal();
        } else {
            driver = runSauce(testinfo);
        }
        sauceDemoApp = new SauceDemoApp(new Browser(driver));
    }

    private RemoteWebDriver runLocal() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeOption());
    }

    private RemoteWebDriver runSauce(TestInfo testinfo) {
        SauceOptions options;

        String platform = System.getProperty("SAUCE_PLATFORM");
        if (platform != null && !"DEFAULT".equals(platform)) {
            options = new SauceOptions();
            options.sauce().setName(testinfo.getDisplayName());
            Path path = Paths.get("src/test/java/com/saucelabs/advancedselenium/saucedemo/config.yml");
            Map<String, Object> capabilities = convertYaml(path, platform);
            options.mergeCapabilities(capabilities);
        } else {
            options = SauceOptions.chrome(getChromeOption())
                    .setName(testinfo.getDisplayName())
                    .setIdleTimeout(Duration.ofSeconds(30))
                    .setUnhandledPromptBehavior(UnhandledPromptBehavior.IGNORE)
                    .setScreenResolution("1280x1024")
                    .build();
        }

        session = new SauceSession(options);
        return session.start();
    }

    private ChromeOptions getChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();

        // Ensure robust handling of race conditions
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        // Start Full Screen
        chromeOptions.addArguments("start-fullscreen");

        // Add Extension
        File ext = new File("src/test/resources/ninja-saucebot.crx");
        chromeOptions.addExtensions(ext);

        // Restore Popup Blocking
        chromeOptions.setExperimentalOption("excludeSwitches",
                Collections.singletonList("disable-popup-blocking"));

        return chromeOptions;
    }

    private Map<String, Object> convertYaml(Path file, String key) {
        InputStream input = null;
        try {
            input = new FileInputStream(file.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Yaml yaml = new Yaml();
        Map<String, Object> yamlData = yaml.load(input);
        return (Map<String, Object>) yamlData.get(key);
    }


    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                System.out.println("Test Failed!");
            } catch (Exception ignored) {
            } finally {
                if (session == null) {
                    sauceDemoApp.getBrowser().quit();
                } else {
                    session.stop(false);
                }
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            try {
                System.out.println("Test Passed!");
            } catch (Exception ignored) {
            } finally {
                if (session == null) {
                    sauceDemoApp.getBrowser().quit();
                } else {
                    session.stop(true);
                }
            }
        }

        @Override
        public void testAborted(ExtensionContext context, Throwable cause) {
        }

        @Override
        public void testDisabled(ExtensionContext context, Optional<String> reason) {
        }
    }
}
