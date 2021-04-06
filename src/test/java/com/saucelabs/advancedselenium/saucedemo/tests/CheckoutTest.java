package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("Luke");
        driver.findElement(By.id("last-name")).sendKeys("Perry");
        driver.findElement(By.id("postal-code")).sendKeys("90210");

        driver.findElement(By.id("continue")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }

    @Test
    public void completeCheckout() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Luke");
        driver.findElement(By.id("last-name")).sendKeys("Perry");
        driver.findElement(By.id("postal-code")).sendKeys("90210");
        driver.findElement(By.id("continue")).click();

        driver.findElement(By.id("finish")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());

        Assertions.assertTrue(driver.findElement(By.className("complete-text")).isDisplayed());
    }
}
