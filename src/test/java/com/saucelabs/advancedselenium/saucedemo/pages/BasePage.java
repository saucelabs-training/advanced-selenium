package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class BasePage {
    protected RemoteWebDriver driver;
    protected String pageUrl;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void visit() {
        driver.get(pageUrl);
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals(pageUrl);
    }
}
