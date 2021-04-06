package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class JourneyTest {
    RemoteWebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void purchaseItems() throws InterruptedException {
        // Go to Sauce Demo Site

        driver.get("https://www.saucedemo.com/");

        // Attempt Sign in with locked user

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorElement = driver.findElement(By.cssSelector("[data-test=error]"));
        Assertions.assertTrue(errorElement.getText().contains("Sorry, this user has been locked out"),
                "Error Not Found");

        // Sign in with Standard User

        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Login Not Successful");

        // Log Out

        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);

        driver.findElement(By.id("logout_sidebar_link")).click();

        Assertions.assertEquals("https://www.saucedemo.com/",
                driver.getCurrentUrl(),
                "Logout Not Successful");

        // Open Bolt T-Shirt About Page

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("item_1_title_link")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=1",
                driver.getCurrentUrl(),
                "Navigation to Details Page Unsuccessful");

        // Add a Bolt T-Shirt to the Cart

        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Item not correctly added to cart");

        // Remove Item from About Page

        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Item not correctly removed from cart");

        // Add a Fleece Onesie, Bike Light & Backpack to the Cart

        driver.findElement(By.id("back-to-products")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        Assertions.assertEquals("3",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Items not correctly added to cart");

        // Remove Bike Light

        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

        Assertions.assertEquals("2",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Bike light not removed from cart");

        // Go to Cart Page

        driver.findElement(By.className("shopping_cart_link")).click();

        Assertions.assertEquals("https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Remove Backpack

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText(),
                "Backpack not removed from cart");

        // Back to Shopping

        driver.findElement(By.id("continue-shopping")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Back to Cart

        driver.findElement(By.className("shopping_cart_link")).click();

        Assertions.assertEquals("https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Go to Checkout Page

        driver.findElement(By.id("checkout")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Cancel

        driver.findElement(By.id("cancel")).click();

        Assertions.assertEquals("https://www.saucedemo.com/cart.html",
                driver.getCurrentUrl(),
                "Canceling from Checkout Unsuccessful");

        // Go back to Checkout Page

        driver.findElement(By.id("checkout")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html",
                driver.getCurrentUrl(),
                "Navigation to Cart Unsuccessful");

        // Try Continuing without entering info

        driver.findElement(By.id("continue")).click();

        Assertions.assertTrue(driver.findElement(By.id("first-name")).getAttribute("class").contains("error"),
                "Expected error not found on page");

        // Enter info and continue

        driver.findElement(By.id("first-name")).sendKeys("Luke");
        driver.findElement(By.id("last-name")).sendKeys("Perry");
        driver.findElement(By.id("postal-code")).sendKeys("90210");

        driver.findElement(By.id("continue")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html",
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");

        // Cancel

        driver.findElement(By.id("cancel")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                driver.getCurrentUrl(),
                "Canceling Unsuccessful");


        // Go Back to 2nd Checkout Page

        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Luke");
        driver.findElement(By.id("last-name")).sendKeys("Perry");
        driver.findElement(By.id("postal-code")).sendKeys("90210");
        driver.findElement(By.id("continue")).click();

        // Finish

        driver.findElement(By.id("finish")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-complete.html",
                driver.getCurrentUrl(),
                "Finishing unsuccessful");

        Assertions.assertTrue(driver.findElement(By.className("complete-text")).isDisplayed());
    }
}
