package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;
import test.java.pages.HomePage;
import test.java.pages.HomePageElements;
import test.java.pages.HomePageMethods;
import test.java.pages.InventoryPage;

public class GoldilocksPOTest extends SauceTestBase {

    @Test
    public void noPageObjectLogin() {
        driver.get("https://www.saucedemo.com/");

        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void imperativeLogin() {
        driver.get("https://www.saucedemo.com/");

        HomePageElements homePageElements = new HomePageElements();
        homePageElements.getUsername().sendKeys("standard_user");
        homePageElements.getPassword().sendKeys("secret_sauce");
        homePageElements.getSubmit().click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    public void singleResponsibilityLogin() {
        HomePageMethods homePageMethods = new HomePageMethods();
        homePageMethods.navigateTo();
        InventoryPage inventoryPage = homePageMethods
                .login("standard_user", "secret_sauce");

        Assert.assertTrue(inventoryPage.isOnPage());
    }

    @Test
    public void justRightLogin() {
        HomePage homePage = new HomePage();
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        Assert.assertTrue(inventoryPage.isOnPage());
    }
}
