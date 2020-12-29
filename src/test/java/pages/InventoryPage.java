package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import test.java.data.Product;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class InventoryPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/inventory.html";

    private static final By INVENTORY_ITEM = By.className("inventory_item");
    private static final By BUTTON = By.tagName("button");

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
        WebElement parent = partialStringMatch(INVENTORY_ITEM, product);
        WebElement button = parent.findElement(BUTTON);
        button.click();
    }

    public void navigateToShoppingCart() {
        locateDisplayedElement("Shopping Cart Link", SHOPPING_CART).click();
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
        wait.until((Function<WebDriver, Object>) driver -> doesElementExist(INVENTORY_ITEM));

        Product product = new Product();
        WebElement parent = partialStringMatch(INVENTORY_ITEM, product.getName());
        parent.findElement(BUTTON).click();

        return product;
    }
}
