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
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);

        Map<String, Integer> timeouts = new HashMap<String, Integer>();
        timeouts.put("pageLoad", 60000);
        timeouts.put("script", 60000);
        chromeOptions.setCapability("timeouts", timeouts);

        String platform = System.getProperty("SELENIUM_PLATFORM");

        if (platform != null && platform.equals("SAUCE")) {
            SauceOptions sauceOptions = new SauceOptions(chromeOptions);
            sauceOptions.setName(testinfo.getDisplayName());

            session = new SauceSession(sauceOptions);
            driver = session.start();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOptions);
        }
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
