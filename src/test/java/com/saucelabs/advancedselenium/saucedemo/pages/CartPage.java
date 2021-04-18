package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class CartPage extends BasePage {
    private final By removeItemButton = By.className("btn_secondary");
    private final By checkoutButton = By.id("checkout");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void removeItemFromCart() {
        getElement("removeItemButton").click();
    }

    public void checkout() {
        getElement("checkoutButton").click();
    }
}
