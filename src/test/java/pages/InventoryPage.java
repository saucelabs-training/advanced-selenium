package test.java.pages;

import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private static WebDriver driver;

    public InventoryPage(WebDriver driver) {
        InventoryPage.driver = driver;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectProduct(String s) {
    }

    public void navigateToShoppingCart() {
    }
}
