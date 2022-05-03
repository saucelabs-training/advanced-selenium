package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

public class FinishPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-complete.html";
    private final TextField completeText = browser.getTextField(By.className("complete-text"));

    public FinishPage(Browser browser) {
        super(browser);
    }

    public boolean isComplete() {
        return completeText.isDisplayed();
    }
}
