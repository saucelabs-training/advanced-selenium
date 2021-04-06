package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartTest {
    ChromeDriver driver;

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
    public void addFromProductPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("item_1_title_link")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    @Test
    public void removeFromProductPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("item_1_title_link")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        driver.findElement(By.id("remove-sauce-labs-bolt-t-shirt")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Item not correctly removed from cart");
    }

    @Test
    public void addFromInventoryPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();

        Assertions.assertEquals("1",
                driver.findElement(By.className("shopping_cart_badge")).getText());
    }

    @Test
    public void removeFromInventoryPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Shopping Cart is not empty");
    }

    @Test
    public void removeFromCartPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("remove-sauce-labs-backpack")).click();

        Assertions.assertTrue(driver.findElements(By.className("shopping_cart_badge")).isEmpty(),
                "Shopping Cart is not empty");
    }
}
