package test.java.pages;

import org.openqa.selenium.By;
import test.java.data.Product;
import test.java.element.Element;
import test.java.element.ElementCollection;
import test.java.exceptions.PageValidationException;

public class InventoryPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/inventory.html";

    private ElementCollection inventoryItems = browser.elements("Inventory Item", By.className("inventory_item"));
    private Element addToCartButton = browser.button("Add To Cart Button", By.tagName("button"));
    private Element shoppingCartLink = browser.element("Shopping Cart Link", By.className("shopping_cart_link"));

    public static InventoryPage visit() {
        InventoryPage inventoryPage = new InventoryPage();
        driver.navigate().to(URL);
        return inventoryPage;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectProduct(String product) {
        Element matchingSubstring = inventoryItems.findMatchingSubstring(product);
        matchingSubstring.findElement(addToCartButton).click();
    }

    public void navigateToShoppingCart() {
        shoppingCartLink.click();
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
