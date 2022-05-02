package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InformationPage {
    private final RemoteWebDriver driver;

    public InformationPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getFirstNameElement() {
        return driver.findElement(By.cssSelector("input[data-test='firstName']"));
    }

    public WebElement getLastNameElement() {
        return driver.findElement(By.cssSelector("input[data-test='lastName']"));
    }

    public WebElement getPostalCodeElement() {
        return driver.findElement(By.cssSelector("input[data-test='postalCode']"));
    }

    public WebElement getContinueButton() {
        return driver.findElement(By.cssSelector("input[data-test='continue']"));
    }
}
