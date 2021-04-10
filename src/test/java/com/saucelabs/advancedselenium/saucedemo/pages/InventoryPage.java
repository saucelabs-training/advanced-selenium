package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class InventoryPage extends BasePage {
    private final By boltShirtLink = By.id("item_1_title_link");
    private final By onesieButton = By.id("add-to-cart-sauce-labs-onesie");
    private final By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    private final By backpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/inventory.html";
    }

    @Override
    public void visit() {
        HomePage homePage = new HomePage(driver);
        homePage.visit();
        homePage.login("standard_user", "secret_sauce");
    }

    public void selectBoltTshirt() {
        driver.findElement(boltShirtLink).click();
    }

    public void addOnesieToCart() {
        driver.findElement(onesieButton).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(addBikeLightButton).click();
    }

    public void removeBikeLightFromCart() {
        driver.findElement(removeBikeLightButton).click();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackButton).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }
}
