package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.By;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class ButtonElement extends Element {
    public ButtonElement(By locator, BasePage page) {
        this(locator, "Button Element", page);
    }

    public ButtonElement(By locator, String description, BasePage page) {
        super(locator, description, page);
    }

    @Override
    public void click() {
        waitForEnabled();
        super.click();
    }
}

