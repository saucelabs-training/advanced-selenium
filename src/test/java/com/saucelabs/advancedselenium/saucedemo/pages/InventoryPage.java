package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class InventoryPage extends BasePage {
    private final Element addItemButton = getElement(By.className("btn_primary"), "Add Item Button");
    private final ButtonElement removeItemButton = getButton(By.className("btn_secondary"), "Remove Item Button");
    private final Element shoppingCartLink = getElement(By.className("shopping_cart_link"), "Shopping Cart Link");

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/inventory.html";
    }

    public void addItemToCart() {
        WebElement item = addItemButton.getRandom();
        item.click();
    }

    public void removeItemFromCart() {
        WebElement item = removeItemButton.getRandom();
        item.click();
    }

    public void goToCart() {
        shoppingCartLink.click();
    }
}
