package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("button[data-test='checkout']");
    private final By removeItemButton = By.cssSelector("button[data-test^='remove-']");

    public CartPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void checkout() {
        click(checkoutButton);
    }

    public void removeItemSuccessfully() {
        HeaderSection headerSection = new HeaderSection(driver);
        Integer before = headerSection.getNumberItemsInCart();
        Integer expected = before - 1;

        removeItem();

        try {
            wait.until((Function<WebDriver, Object>) driver -> expected.equals(headerSection.getNumberItemsInCart()));
        } catch (TimeoutException ex) {
            String what = "Removing item unsuccessful; ";
            String after = headerSection.getNumberItemsInCart().toString();
            throw new PageValidationException(what + "Expected: " + expected + ", but found: " + after);
        }
    }

    private void removeItem() {
        List<WebElement> items = driver.findElements(removeItemButton);
        items.get(new Random().nextInt(items.size())).click();
    }
}
