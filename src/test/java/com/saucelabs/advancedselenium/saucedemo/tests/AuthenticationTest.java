package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class AuthenticationTest extends BaseTest {
    @Test
    public void signInUnsuccessful() {
        HomePage homePage = new HomePage(driver);
        driver.get(HomePage.URL);

        homePage.getUsernameElement().sendKeys("locked_out_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();

        Assertions.assertTrue(homePage.getErrorElement().getText().contains("Sorry, this user has been locked out"),
                "Error Not Found");
    }

    @Test
    public void signInSuccessful() {
        HomePage homePage = new HomePage(driver);
        driver.get(HomePage.URL);

        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();

        Assertions.assertEquals(InventoryPage.URL,
                driver.getCurrentUrl(),
                "Login Not Successful");
    }

    @Test
    public void logout() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        driver.get(HomePage.URL);

        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getMenuButton().click();
        Thread.sleep(1000);

        inventoryPage.getLogoutLink().click();

        Assertions.assertEquals(HomePage.URL,
                driver.getCurrentUrl(),
                "Logout Not Successful");
    }
}
