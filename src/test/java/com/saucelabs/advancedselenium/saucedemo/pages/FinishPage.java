package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.By;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

public class FinishPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final TextField completeText = browser.getTextField(By.className("complete-text"));

    public FinishPage(SauceDemoApp app) {
        super(app);
    }

    public boolean isComplete() {
        return completeText.isDisplayed();
    }
}
