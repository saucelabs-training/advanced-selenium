package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CheckoutTest extends BaseTest {
    @BeforeEach
    public void loginAndGoToCartWithItem() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Test
    public void goodInfo() {
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");

        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html",
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        driver.findElement(By.cssSelector("button[data-test='finish']")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());

        Assertions.assertTrue(driver.findElement(By.className("complete-text")).isDisplayed());
    }
}
