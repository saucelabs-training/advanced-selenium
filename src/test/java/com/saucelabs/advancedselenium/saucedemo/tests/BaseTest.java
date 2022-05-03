package com.saucelabs.advancedselenium.saucedemo.tests;

import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Optional;

public class BaseTest {
    protected SauceDemoApp sauceDemoApp;
    protected SauceSession session;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeAll
    public static void toggleExecution() {
        // This would normally be toggled via CI tool ENV or similar
        System.setProperty("SELENIUM_PLATFORM", "SAUCE");
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
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(org.openqa.selenium.PageLoadStrategy.NONE);
        return new ChromeDriver(options);
    }

    private RemoteWebDriver runSauce(TestInfo testinfo) {
        SauceOptions options = SauceOptions.chrome()
                .setName(testinfo.getDisplayName())
                .setPageLoadStrategy(PageLoadStrategy.NONE)
                .build();
        session = new SauceSession(options);
        return session.start();
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
