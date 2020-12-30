package test.java.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonElement extends Element {

    public ButtonElement(String description, By locator, WebDriver driver) {
        super(description, locator, driver);
    }

    @Override
    public void click() {
        waitForEnabled();
        super.click();
    }
}
