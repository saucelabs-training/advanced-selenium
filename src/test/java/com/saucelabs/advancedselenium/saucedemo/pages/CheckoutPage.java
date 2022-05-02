package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-two.html";
    private final By finishButton = By.cssSelector("button[data-test='finish']");

    public CheckoutPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void finish() {
        driver.findElement(finishButton).click();
    }

    public boolean isAddingInfoSuccessful() {
        return URL.equals(driver.getCurrentUrl());
    }
}
