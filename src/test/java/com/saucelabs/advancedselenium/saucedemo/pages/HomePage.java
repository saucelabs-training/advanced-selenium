package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    private RemoteWebDriver driver;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getPasswordElement() {
        return driver.findElement(By.id("password"));
    }

    public WebElement getUsernameElement() {
        return driver.findElement(By.id("user-name"));
    }

    public WebElement getSubmitElement() {
        return driver.findElement(By.id("login-button"));
    }

    public WebElement getErrorElement() {
        return driver.findElement(By.cssSelector("[data-test=error]"));
    }

    public String getUrl() {
        return "https://www.saucedemo.com/";
    }
}
