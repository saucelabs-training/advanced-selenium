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
        homePage.loginSuccessfully("standard_user", "secret_sauce");
    }

    public void selectBoltTshirt() {
        getElement("boltShirtLink").click();
    }

    public void addOnesieToCart() {
        getElement("onesieButton").click();
    }

    public void addBikeLightToCart() {
        getElement("addBikeLightButton").click();
    }

    public void removeBikeLightFromCart() {
        getElement("removeBikeLightButton").click();
    }

    public void addBackpackToCart() {
        getElement("backpackButton").click();
    }

    public void goToCart() {
        getElement("shoppingCartLink").click();
    }
}
