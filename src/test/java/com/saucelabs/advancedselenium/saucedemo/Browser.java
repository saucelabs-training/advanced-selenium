package com.saucelabs.advancedselenium.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

import java.time.Duration;
import java.util.concurrent.Callable;

public class Browser {
    private final RemoteWebDriver driver;
    protected final WebDriverWait wait;

    public Browser(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public RemoteWebDriver getDriver() {
        return this.driver;
    }

    public Button getButton(By locator) {
        return new Button(this, locator);
    }

    public Button getButton(String dataTest) {
        By locator = By.cssSelector("*[data-test='" + dataTest + "']");
        return new Button(this, locator);
    }

    public TextField getTextField(By locator) {
        return new TextField(this, locator);
    }

    public TextField getTextField(String dataTest) {
        By locator = By.cssSelector("*[data-test='" + dataTest + "']");
        return new TextField(this, locator);
    }

    public Element getElement(By locator) {
        return new Element(this, locator);
    }

    public ElementList getElements(By locator) {
        return new ElementList(this, locator);
    }

    public ElementList getElements(String dataTest) {
        By locator = By.cssSelector("*[data-test='" + dataTest + "']");
        return new ElementList(this, locator);
    }
    public void quit() {
        driver.quit();
    }

    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public Object waitUntil(Callable<Object> block) {
        return wait.until(driver -> {
            try {
                return block.call();
            } catch (WebDriverException e) {
                throw e;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void addCookie(String name, String value) {
        driver.manage().addCookie(new Cookie(name, value));
    }

    public Cookie getCookie(String name) {
        return driver.manage().getCookieNamed(name);
    }
}
