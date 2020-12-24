package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private static WebDriver driver;

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.id("login-button");

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
    }

    public void navigateTo() {
        driver.get("https://www.saucedemo.com");
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(SUBMIT).click();
    }
}
