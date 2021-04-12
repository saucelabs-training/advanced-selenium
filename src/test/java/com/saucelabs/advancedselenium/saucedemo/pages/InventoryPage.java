package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InventoryPage {
    private RemoteWebDriver driver;

    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By boltShirtLink = By.id("item_1_title_link");
    private final By onesieButton = By.id("add-to-cart-sauce-labs-onesie");
    private final By shoppingCartBadgeImage = By.className("shopping_cart_badge");
    private final By addBikeLightButton = By.id("add-to-cart-sauce-labs-bike-light");
    private final By removeBikeLightButton = By.id("remove-sauce-labs-bike-light");
    private final By backpackButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By shoppingCartLink = By.className("shopping_cart_link");

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public HomePage logout() {
        driver.findElement(menuButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(logoutLink).click();
        return new HomePage(driver);
    }

    public ProductPage selectBoltTshirt() {
        driver.findElement(boltShirtLink).click();
        return new ProductPage(driver);
    }

    public InventoryPage addOnesieToCart() {
        driver.findElement(onesieButton).click();
        return new InventoryPage(driver);
    }

    public int cartItems() {
        if (driver.findElements(shoppingCartBadgeImage).isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(driver.findElement(shoppingCartBadgeImage).getText());
        }
    }

    public InventoryPage addBikeLightToCart() {
        driver.findElement(addBikeLightButton).click();
        return new InventoryPage(driver);
    }

    public InventoryPage removeBikeLightFromCart() {
        driver.findElement(removeBikeLightButton).click();
        return new InventoryPage(driver);
    }

    public InventoryPage addBackpackToCart() {
        driver.findElement(backpackButton).click();
        return new InventoryPage(driver);
    }

    public CartPage goToCart() {
        driver.findElement(shoppingCartLink).click();
        return new CartPage(driver);
    }
}
