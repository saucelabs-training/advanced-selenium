package test.java.pages;

import org.openqa.selenium.By;
import test.java.data.User;

public class SauceDemoPage extends BasePage {
    public static SauceDemoPage visit() {
        return new SauceDemoPage();
    }
    private static final By MISSING = By.id("not-an-element");

    public void login(User data) {
    }

    public void locateElementCustomError() {
        locateElement("Missing Element", MISSING);
    }
}
