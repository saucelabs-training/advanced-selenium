package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.pages.BasePage;

public class LocalTestBase {
    public RemoteWebDriver driver = null;

    @Rule
    public LocalTestWatcher localTestWatcher = new LocalTestWatcher();

    @Before
    public void startSession() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

//        toggle on for examples as needed
//        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);

        driver = new ChromeDriver(chromeOptions);
        BasePage.setDriver(driver);
    }

    private static class LocalTestWatcher extends TestWatcher {
        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("Failed :(");
            try {
                doSomethingWithResults(description);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            BasePage.getDriver().quit();
        }

        @Override
        protected void succeeded(Description description) {
            System.out.println("Passed! :) :)");
            BasePage.getDriver().quit();
        }

        private void doSomethingWithResults(Description description) throws Exception {
            // Something that might fail
        }
    }
}

