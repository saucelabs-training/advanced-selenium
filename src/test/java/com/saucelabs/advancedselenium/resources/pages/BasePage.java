package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;

import java.time.Duration;

public abstract class BasePage {
    public Duration defaultWaitTime = Duration.ofSeconds(20);
    protected RemoteWebDriver driver;
    protected String pageUrl;
    protected WebDriverWait wait;

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, defaultWaitTime);
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void visit() {
        driver.get(pageUrl);
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals(pageUrl);
    }

    public Element getElement(By locator, String description) {
        return new Element(locator, description, this);
    }
}
