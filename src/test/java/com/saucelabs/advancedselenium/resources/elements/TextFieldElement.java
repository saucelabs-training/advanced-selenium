package test.java.com.saucelabs.advancedselenium.resources.elements;

import org.openqa.selenium.By;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class TextFieldElement extends Element {
    public TextFieldElement(By locator, BasePage page) {
        this(locator, "Text Field Element", page);
    }

    public TextFieldElement(By locator, String description, BasePage page) {
        super(locator, description, page);
    }
}
