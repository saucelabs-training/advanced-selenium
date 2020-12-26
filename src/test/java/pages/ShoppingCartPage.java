package test.java.pages;

import test.java.exceptions.PageValidationException;

public class ShoppingCartPage extends BasePage {
    public static ShoppingCartPage visit() {
        return new ShoppingCartPage();
    }

    public void checkOutSuccessfully() {
        throw new PageValidationException("Need to implement checking out");
    }

    public boolean isOnPage() {
        return false;
    }

    public void validateItem(String product) {
        throw new PageValidationException("Need to implement validating items in cart");
    }
}
