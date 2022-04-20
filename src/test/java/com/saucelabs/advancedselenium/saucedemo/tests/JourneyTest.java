package com.saucelabs.advancedselenium.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class JourneyTest {
    RemoteWebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/selectorsHub.crx"));
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void userJourney() throws InterruptedException {
        // Go to Sauce Demo Site
        driver.get("https://www.saucedemo.com/");

        // Attempt Sign in with locked user
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("locked_out_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();

        // Validate error
        WebElement errorElement = driver.findElement(By.cssSelector("[data-test='error']"));
        Assertions.assertTrue(errorElement.getText().contains("Sorry, this user has been locked out"),
                "Error Not Found");

        // Sign in with Standard User
        driver.findElement(By.cssSelector("input[data-test='username']")).clear();
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();

        // Validate authenticated
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Login Not Successful");

        // Log Out
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("logout_sidebar_link")).click();

        // Validate unauthenticated
        Assertions.assertEquals("https://www.saucedemo.com/",
                driver.getCurrentUrl(),
                "Logout Not Successful");

        // Log In Again
        driver.findElement(By.cssSelector("input[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[data-test='password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input[data-test='login-button']")).click();

        // Open Bolt T-Shirt About Page
        driver.findElement(By.id("item_1_title_link")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=1",
                driver.getCurrentUrl(),
                "Navigation to Details Page Unsuccessful");

        // Add a Bolt T-Shirt to the Cart
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bolt-t-shirt']")).click();

        // Verify in cart
        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Item not correctly added to cart");

        // Remove Item
        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bolt-t-shirt']")).click();

        // Verify not in cart
        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Item not correctly removed from cart");

        // Go back to Products page
        driver.findElement(By.cssSelector("button[data-test='back-to-products']")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Add a Fleece Jacket, Bike Light & Backpack to the Cart
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-onesie']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-bike-light']")).click();
        driver.findElement(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']")).click();

        // Verify 3 items in cart
        Assertions.assertEquals("3",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Items not correctly added to cart");

        // Remove Bike Light
        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-bike-light']")).click();

        // Verify 2 items in art
        Assertions.assertEquals("2",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Bike light not removed from cart");

        // Go to Cart Page
        driver.findElement(By.className("shopping_cart_link")).click();

        // Verify Jacket & backpack are in cart
        Assertions.assertEquals("https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Remove Backpack
        driver.findElement(By.cssSelector("button[data-test='remove-sauce-labs-backpack']")).click();

        // Verify backpack not in cart
        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Backpack not removed from cart");

        // Back to Shopping
        driver.findElement(By.id("continue-shopping")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Back to Cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Go to Checkout Page
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Cancel
        driver.findElement(By.cssSelector("button[data-test='cancel']")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "Canceling from Checkout Unsuccessful");

        // Go back to Checkout Page
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();

        // Select Continue
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        // Verify errors
        Assertions.assertTrue(driver.findElement(By.cssSelector("input[data-test='firstName']"))
                        .getAttribute("class").contains("error"),
                "Expected error not found on page");

        // Enter info and continue
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html",
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");

        // Cancel
        driver.findElement(By.cssSelector("button[data-test='cancel']")).click();

        // Verify correct page
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Canceling Unsuccessful");

        // Go to cart
        driver.findElement(By.className("shopping_cart_link")).click();

        // Go to checkout
        driver.findElement(By.cssSelector("button[data-test='checkout']")).click();

        // re-enter info
        driver.findElement(By.cssSelector("input[data-test='firstName']")).sendKeys("Luke");
        driver.findElement(By.cssSelector("input[data-test='lastName']")).sendKeys("Perry");
        driver.findElement(By.cssSelector("input[data-test='postalCode']")).sendKeys("90210");
        driver.findElement(By.cssSelector("input[data-test='continue']")).click();

        // Verify correct item
        WebElement product = driver.findElement(By.className("inventory_item_name"));
        Assertions.assertEquals("Sauce Labs Onesie",
                product.getText(),
                "Wrong item in cart");

        // Finish
        driver.findElement(By.cssSelector("button[data-test='finish']")).click();

        // Verify success message
        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html",
                driver.getCurrentUrl(),
                "Finishing unsuccessful");

        Assertions.assertTrue(driver.findElement(By.className("complete-text")).isDisplayed());
    }
}
