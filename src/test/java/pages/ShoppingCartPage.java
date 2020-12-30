package test.java.pages;

import org.openqa.selenium.By;
import test.java.data.Product;
import test.java.element.Element;
import test.java.element.ElementCollection;
import test.java.exceptions.PageValidationException;

public class ShoppingCartPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/cart.html";

    private ElementCollection inventoryItems = browser.elements("Inventory Item", By.className("inventory_item_name"));
    private Element checkoutPageButton = browser.element("Checkout Page Button", By.className("checkout_button"));

    public static ShoppingCartPage visit() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
        driver.navigate().to(URL);
        return shoppingCartPage;
    }

    public void checkOutSuccessfully() {
        checkoutPageButton.click();
    }

    public boolean isOnPage() {
        return false;
    }

    public void validateItem(String product) {
        try {
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
