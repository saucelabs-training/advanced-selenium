package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Objects;

public class ProductPage extends BasePage {
    private final By addToCartButton = By.cssSelector("button[data-test^='add-to-cart-']");
    private final By removeFromCartButton = By.cssSelector("button[data-test^='remove']");

    public ProductPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void addItemToCartSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before + 1;

        driver.findElement(addToCartButton).click();

        Integer after = headerSection.getNumberItemsInCart();
        if (!Objects.equals(after, expected)) {
            throw new PageValidationException("Adding item unsuccessful; Expected: " + expected + ", but found: " + after);
        }
    }

    public void removeItemFromCartSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        driver.findElement(removeFromCartButton).click();

        Integer after = headerSection.getNumberItemsInCart();
        if (!Objects.equals(after, expected)) {
            throw new PageValidationException("Removing item unsuccessful; Expected: " + expected + ", but found: " + after);
        }
    }
}
