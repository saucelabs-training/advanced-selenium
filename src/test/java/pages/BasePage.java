package test.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.element.Browser;

public class BasePage {
    protected static Browser browser;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final int DEFAULT_WAIT_TIME = 20;

    public static Browser getBrowser() {
        return browser;
    }

    public static void createBrowser(RemoteWebDriver driver) {
        browser = new Browser(driver);
        BasePage.driver = browser.getDriver();
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }
}

