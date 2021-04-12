package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {
    private RemoteWebDriver driver;

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void removeBackpackFromCart() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    public int cartItems() {
        return Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    public InformationPage checkout() {
        driver.findElement(By.id("checkout")).click();
        return new InformationPage(driver);
    }
}
