package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.util.Random;

public class ProductPage extends BasePage {
    private final ButtonElement addButton = getButton(By.className("btn_primary"), "Add Button");
    private final ButtonElement removeButton = getButton(By.cssSelector(".btn_secondary.btn_inventory"), "Remove Button");

    public ProductPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/inventory-item.html";
    }

    @Override
    public void visit() {
        driver.get(pageUrl + "?id=" + new Random().nextInt(6));
    }

    public void addToCart() {
        addButton.click();
    }

    public void removeFromCart() {
        removeButton.click();
    }
}
