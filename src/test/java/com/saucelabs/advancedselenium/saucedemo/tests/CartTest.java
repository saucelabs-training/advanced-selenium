package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CartTest extends BaseTest {
    public void login() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();
    }

    @Test
    public void addFromProductPage() {
        login();
        driver.findElement(By.id("item_1_title_link")).click();

        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Item not correctly added to cart");
    }

    @Test
    public void removeFromProductPage() {
        login();
        driver.findElement(By.id("item_1_title_link")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")).click();

        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bolt-t-shirt']")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        login();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    @Test
    public void removeFromInventoryPage() {
        login();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bike-light']")).click();

        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bike-light']")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Shopping Cart is not empty");
    }

    @Test
    public void removeFromCartPage() {
        login();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-backpack']")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Shopping Cart is not empty");
    }
}
