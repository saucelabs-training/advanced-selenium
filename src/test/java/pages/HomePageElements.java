package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePageElements extends OldBasePage {
    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.id("login-button");

    public WebElement getUsername() {
        return driver.findElement(USERNAME);
    }

    public WebElement getPassword() {
        return driver.findElement(PASSWORD);
    }

    public WebElement getSubmit() {
        return driver.findElement(SUBMIT);
    }
}
