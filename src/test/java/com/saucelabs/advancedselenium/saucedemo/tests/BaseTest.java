package com.saucelabs.advancedselenium.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Optional;

public class BaseTest {
    ChromeDriver driver = null;

    @RegisterExtension
    public MyTestWatcher myTestWatcher = new MyTestWatcher();

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
    }

    public class MyTestWatcher implements TestWatcher {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            try {
                System.out.println("Test Failed!");
            } catch (Exception ignored) {
            } finally {
                driver.quit();
            }
        }

        @Override
        public void testSuccessful(ExtensionContext context) {
            try {
                System.out.println("Test Passed!");
            } catch (Exception ignored) {
            } finally {
                driver.quit();
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
