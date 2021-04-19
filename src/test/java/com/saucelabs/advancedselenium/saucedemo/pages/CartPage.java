package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class CartPage extends BasePage {
    private final Element removeItemButton = getElement(By.className("btn_secondary"), "Remove Item Button");
    private final Element checkoutButton = getElement(By.id("checkout"), "Checkout Button");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void removeItemFromCart() {
        removeItemButton.click();
    }

    public void checkout() {
        checkoutButton.click();
    }
}
