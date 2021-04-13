package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends BasePage {
    private final By addButton = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final By removeButton = By.id("remove-sauce-labs-bolt-t-shirt");

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
