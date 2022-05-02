package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");

    public CartPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }

    public void removeItemSuccessfully(Product product) {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItem(product);

        Integer after = headerSection.getNumberItemsInCart();

        if (!Objects.equals(after, expected)) {
            throw new PageValidationException("Removing item unsuccessful; Expected: " + expected + ", but found: " + after);
        }
    }

    private void removeItem(Product product) {
        String cssSelector = "button[data-test='remove-" + product.getId() + "']";
        driver.findElement(By.cssSelector(cssSelector)).click();
    }
}
