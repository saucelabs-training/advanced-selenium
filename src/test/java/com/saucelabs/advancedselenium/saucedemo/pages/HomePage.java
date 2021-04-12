package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {
    private RemoteWebDriver driver;

    public HomePage(RemoteWebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        this.driver = driver;
    }

    public InventoryPage login(String user, String password) {
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        return new InventoryPage(driver);
    }

    public String getError() {
        return driver.findElement(By.cssSelector("[data-test=error]")).getText();
    }
}
