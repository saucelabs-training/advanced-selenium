package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BaseTest {
    RemoteWebDriver driver = null;
    SauceSession session = null;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeEach
    public void setUp(TestInfo testinfo) {
        String platform = System.getProperty("SELENIUM_PLATFORM");
        if (platform != null && platform.equals("SAUCE")) {
            SauceOptions sauceOptions = new SauceOptions();
            sauceOptions.setName(testinfo.getDisplayName());

            String configs = System.getProperty("PLATFORM_VALUES");
            if (configs != null) {
                Map<String, Object> map = serialize(configs);

                sauceOptions.mergeCapabilities(map);
            }

            session = new SauceSession(sauceOptions);
            driver = session.start();
        } else {
            // TODO: Show how to iterate over Mutable Capabilities for these
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();

            // Start Full Screen
            chromeOptions.addArguments("start-fullscreen");

            // Add Extension
            File ext = new File("src/test/java/com/saucelabs/advancedselenium/saucedemo/ninja-saucebot.crx");
            chromeOptions.addExtensions(ext);

            // Restore Popup Blocking
            chromeOptions.setExperimentalOption("excludeSwitches",
                    Collections.singletonList("disable-popup-blocking"));

            chromeOptions.setAcceptInsecureCerts(true);
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

            Map<String, Integer> timeouts = new HashMap<>();
            timeouts.put("pageLoad", 60000);
            timeouts.put("script", 60000);
            chromeOptions.setCapability("timeouts", timeouts);

            driver = new ChromeDriver(chromeOptions);
        }
    }

    public Map<String, Object> serialize(String key) {
        String path = "src/test/java/com/saucelabs/advancedselenium/saucedemo/config.yml";
        InputStream input = null;
        try {
            input = new FileInputStream(path);
        } catch (FileNotFoundException ignored) {
        }
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(input);
        return (Map<String, Object>) data.get(key);
    }

    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                System.out.println("Test Failed!");
            } catch (Exception ignored) {
            } finally {
                if (session == null) {
                    driver.quit();
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
                    driver.quit();
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
