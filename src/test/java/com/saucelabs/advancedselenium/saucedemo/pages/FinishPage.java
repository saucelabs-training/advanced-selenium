package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;

public class FinishPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final By completeText = By.className("complete-text");

    public FinishPage(RemoteWebDriver driver) {
        super(driver);
    }

    public boolean isComplete() {
        return new Element(driver, completeText).isDisplayed();
    }
}
