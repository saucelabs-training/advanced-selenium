package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTest {
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
    public void cancelFromCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("continue-shopping")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void cancelFromInfoPage() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("cancel")).click();

        Assertions.assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
    }

    @Test
    public void cancelFromCheckoutPage() {
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

        driver.findElement(By.id("cancel")).click();

        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void startCheckout() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
    }
}
