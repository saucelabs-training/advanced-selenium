package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends BasePage {
    private final By addButton = By.className("btn_primary");
    private final By removeButton = By.cssSelector(".btn_secondary.btn_inventory");

    public ProductPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        driver.findElement(addButton).click();
    }

    public void removeFromCart() {
        driver.findElement(removeButton).click();
    }
}
