package test.java.pages;

public class InventoryPage extends BasePage {
    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectProduct(String s) {
    }

    public void navigateToShoppingCart() {
    }
}
