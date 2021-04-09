package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());

        homePage.getUsernameElement().sendKeys("locked_out_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();

        Assertions.assertTrue(homePage.getErrorElement().getText().contains("Sorry, this user has been locked out"));
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());

        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assertions.assertEquals(inventoryPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    public void logout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.getMenuButton().click();
        Thread.sleep(1000);
        inventoryPage.getLogoutLink().click();

        Assertions.assertEquals(homePage.getUrl(), driver.getCurrentUrl());
    }
}
