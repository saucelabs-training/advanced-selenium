package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocalTestBase {
    public RemoteWebDriver driver = null;

    @Rule
    public LocalTestWatcher localTestWatcher = new LocalTestWatcher();

    @Before
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        driver = new ChromeDriver(chromeOptions);
    }

    @After
    public void shutDown() {
        driver.quit();
    }

    private static class LocalTestWatcher extends TestWatcher {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Failed :(");
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("Passed! :) :)");;
        }
    }
}
