package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class CartPage {
    private final RemoteWebDriver driver;

    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void removeItem(Product product) {
        String cssSelector = "button[data-test='remove-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = driver.findElements(shoppingCartBadge);
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }
}
