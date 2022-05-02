package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class HeaderSection extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By shoppingCartBadge = By.className("shopping_cart_badge");

    public HeaderSection(RemoteWebDriver driver) {
        this.driver = driver;
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
        validateLoggedOut();
    }

    private void logOut() {
        driver.findElement(menuButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(logoutLink).click();
    }

    private void validateLoggedOut() {
        if (isLoggedIn()) {
            throw new PageValidationException("User is still logged in;");
        }
    }
}
