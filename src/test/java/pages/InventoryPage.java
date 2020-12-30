package test.java.pages;

import org.openqa.selenium.By;
import test.java.data.Product;
import test.java.element.Element;
import test.java.element.ElementCollection;
import test.java.exceptions.PageValidationException;

public class InventoryPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/inventory.html";

    private ElementCollection inventoryItems = getElements("Inventory Item", By.className("inventory_item"));
    private Element addToCartButton = getElement("Add To Cart Button", By.tagName("button"));

    public static InventoryPage visit() {
        InventoryPage inventoryPage = new InventoryPage();
        driver.navigate().to(URL);
        return inventoryPage;
    }

    private static final By SHOPPING_CART = By.className("shopping_cart_link");

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectProduct(String product) {
        Element matchingSubstring = inventoryItems.findMatchingSubstring(product);
        matchingSubstring.findElement(addToCartButton).click();
    }

    public void navigateToShoppingCart() {
        getElement("Shopping Cart Button", SHOPPING_CART).click();
        validateSuccessfulNavigation();
    }

    public void validateSuccessfulNavigation()  {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(URL)) {
            String msg = "Navigation away from InventoryPage was not successful; Current URL: " + currentUrl;
            throw new PageValidationException(msg);
        }
    }

    public void selectProduct(Product product) {
        selectProduct(product.getName());
    }

    public Product selectProduct() {
        Product product = new Product();
        selectProduct(product);
        return product;
    }
}
