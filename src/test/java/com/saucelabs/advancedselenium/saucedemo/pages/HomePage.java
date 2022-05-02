package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    public static final String URL = "https://www.saucedemo.com/";
    private final RemoteWebDriver driver;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getUsernameElement() {
        return driver.findElement(By.cssSelector("input[data-test='username']"));
    }

    public WebElement getPasswordElement() {
        return driver.findElement(By.cssSelector("input[data-test='password']"));
    }

    public WebElement getSubmitElement() {
        return driver.findElement(By.cssSelector("input[data-test='login-button']"));
    }

    public WebElement getErrorElement() {
        return driver.findElement(By.cssSelector("[data-test=error]"));
    }
}
