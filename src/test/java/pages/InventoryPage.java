package test.java.pages;

import test.java.exceptions.PageValidationException;

public class InventoryPage extends BasePage {
    public static InventoryPage visit() {
        return new InventoryPage();
    }

    public boolean isOnPage() {
        return false;
    }

    public void selectProduct(String product) {
        throw new PageValidationException("Need to implement selecting product");
    }

    public void navigateToShoppingCart() {
        throw new PageValidationException("Need to implement navigating to cart");
    }

    public void validateSuccessfulNavigation()  {
        throw new PageValidationException("Need to implement validating navigation");
    }
}
