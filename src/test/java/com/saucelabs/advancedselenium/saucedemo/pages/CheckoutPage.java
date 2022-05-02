package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final RemoteWebDriver driver;

    public CheckoutPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFinishButton() {
        return driver.findElement(By.cssSelector("button[data-test='finish']"));
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public FinishPage finish() {
        getFinishButton().click();
        return new FinishPage(driver);
    }
}
