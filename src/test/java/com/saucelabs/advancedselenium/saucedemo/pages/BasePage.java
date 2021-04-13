package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class BasePage<T extends BasePage<T>> {
    protected RemoteWebDriver driver;
    protected String pageUrl;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public T visit() {
        driver.get(pageUrl);
        return (T) this;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals(pageUrl);
    }
}
