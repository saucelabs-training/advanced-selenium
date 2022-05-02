package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FinishPage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final RemoteWebDriver driver;

    public FinishPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getCompleteElement() {
        return driver.findElement(By.className("complete-text"));
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public boolean isComplete() {
        return getCompleteElement().isDisplayed();
    }
}
