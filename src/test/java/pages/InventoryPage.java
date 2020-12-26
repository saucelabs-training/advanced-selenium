package test.java.pages;

import org.openqa.selenium.By;
import test.java.exceptions.PageValidationException;

public class InventoryPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/cart.html";

    private static final By SHOPPING_CART = By.className("shopping_cart_link");

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }

    public void selectProduct(String s) {
    }

    public void navigateToShoppingCart() {
        locateDisplayedElement("Shopping Cart Link", SHOPPING_CART).click();
    }

    public void validateSuccessfulNavigation()  {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(URL)) {
            String msg = "Navigation away from InventoryPage was not successful; Current URL: " + currentUrl;
            throw new PageValidationException(msg);
        }
    }
}
