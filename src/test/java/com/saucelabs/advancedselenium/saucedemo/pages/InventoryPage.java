package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

import java.util.List;
import java.util.Random;

public class InventoryPage extends BasePage {
    private final By inventoryItemLink = By.className("inventory_item_img");
    private final By addItemButton = By.className("btn_primary");
    private final By removeItemButton = By.className("btn_secondary");

    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/inventory.html";
    }

    @Override
    public void visit() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        homePage.loginSuccessfully();
    }

    public void selectItem() {
        List<WebElement> inventoryItems = getElements("inventoryItemLink");
        WebElement item = inventoryItems.get(new Random().nextInt(inventoryItems.size()));
        item.click();
    }

    public void addItemToCart() {
        List<WebElement> inventoryItems = getElements("addItemButton");
        WebElement item = inventoryItems.get(new Random().nextInt(inventoryItems.size()));
        item.click();
    }

    public void removeItemFromCart() {
        List<WebElement> inventoryItems = getElements("removeItemButton");
        WebElement item = inventoryItems.get(new Random().nextInt(inventoryItems.size()));
        item.click();
    }

    public void goToCart() {
        getElement("shoppingCartLink").click();
    }
}
