package test.java.pages;

import org.openqa.selenium.By;
import test.java.data.Product;
import test.java.element.ElementCollection;
import test.java.exceptions.PageValidationException;

public class ShoppingCartPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/cart.html";
    private static final By INVENTORY_ITEM_NAME = By.className("inventory_item_name");
    private static final By CHECKOUT_PAGE = By.className("checkout_button");

    public static ShoppingCartPage visit() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        driver.navigate().to(URL);
        return shoppingCartPage;
    }

    public void checkOutSuccessfully() {
        getElement("Checkout Page Button", CHECKOUT_PAGE).click();
    }

    public boolean isOnPage() {
        return false;
    }

    public void validateItem(String product) {
        try {
            ElementCollection inventoryItems = getElements("Inventory Item", INVENTORY_ITEM_NAME);
            inventoryItems.findMatchingSubstring(product);
        } catch (PageValidationException e) {
            throw new PageValidationException("Unable to find this product in the cart: "
                    + product + "; " + e.toString());
        }
    }

    public void validateItem(Product swag1) {
        validateItem(swag1.getName());
    }
}
