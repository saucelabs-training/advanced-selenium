package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class CartPage extends BasePage {
    private final ButtonElement removeItemButton = getButton(By.className("btn_secondary"), "Remove Item Button");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/cart.html";
    }

    public void removeItemFromCart() {
        removeItemButton.click();
    }
}
