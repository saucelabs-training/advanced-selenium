package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    private RemoteWebDriver driver;

    private final By usernameTextField = By.id("user-name");
    private final By passwordTextField = By.id("password");
    private final By submitButton = By.id("login-button");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public HomePage(RemoteWebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        this.driver = driver;
    }

    public InventoryPage login(String user, String password) {
        driver.findElement(usernameTextField).sendKeys(user);
        driver.findElement(passwordTextField).sendKeys(password);
        driver.findElement(submitButton).click();
        return new InventoryPage(driver);
    }

    public String getError() {
        return driver.findElement(errorElement).getText();
    }
}
