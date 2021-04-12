package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InventoryPage {
    private RemoteWebDriver driver;

    public InventoryPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public HomePage logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.id("logout_sidebar_link")).click();
        return new HomePage(driver);
    }

    public ProductPage selectBoltTshirt() {
        driver.findElement(By.id("item_1_title_link")).click();
        return new ProductPage(driver);
    }

    public InventoryPage addOnesieToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        return new InventoryPage(driver);
    }

    public int cartItems() {
        if (driver.findElements(By.className("shopping_cart_badge")).isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
        }
    }

    public InventoryPage addBikeLightToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        return new InventoryPage(driver);
    }

    public InventoryPage removeBikeLightFromCart() {
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        return new InventoryPage(driver);
    }

    public InventoryPage addBackpackToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        return new InventoryPage(driver);
    }

    public CartPage goToCart() {
        driver.findElement(By.className("shopping_cart_link")).click();
        return new CartPage(driver);
    }
}
