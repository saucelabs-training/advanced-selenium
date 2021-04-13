package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;

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
        if (pageUrl == null) {
            throw new PageValidationException("Can not evaluate if on a given page if pageUrl is not defined");
        }
        return driver.getCurrentUrl().equals(pageUrl);
    }
}
