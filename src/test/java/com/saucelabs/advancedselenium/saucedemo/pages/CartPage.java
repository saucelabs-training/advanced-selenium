package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class CartPage extends BasePage {
    private final By removeBackpackButton = By.id("remove-sauce-labs-backpack");
    private final By checkoutButton = By.id("checkout");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void removeBackpackFromCart() {
        getElement("removeBackpackButton").click();
    }

    public void checkout() {
        getElement("checkoutButton").click();
    }
}
