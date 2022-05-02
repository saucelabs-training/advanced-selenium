package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;

import java.util.List;
import java.util.function.Function;

public class HeaderSection extends BasePage {
    private final Element menuButton = new Element(driver, By.id("react-burger-menu-btn"));
    private final Element logoutLink = new Element(driver, By.id("logout_sidebar_link"));
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public HeaderSection(RemoteWebDriver driver) {
        super(driver);
    }

    public Integer getNumberItemsInCart() {
        List<WebElement> cartNumberElements = driver.findElements(shoppingCartBadge);
        if (cartNumberElements.isEmpty()) {
            return 0;
        } else {
            return Integer.valueOf(cartNumberElements.get(0).getText());
        }
    }

    public boolean isLoggedIn() {
        return InventoryPage.URL.equals(driver.getCurrentUrl());
    }

    public void logOutSuccessfully() {
        logOut();

        try {
            wait.until((Function<WebDriver, Object>) driver -> !isLoggedIn());
        } catch (TimeoutException ex) {
            throw new PageValidationException("User is still logged in;");
        }
    }

    private void logOut() {
        menuButton.click();
        logoutLink.click();
    }
}
