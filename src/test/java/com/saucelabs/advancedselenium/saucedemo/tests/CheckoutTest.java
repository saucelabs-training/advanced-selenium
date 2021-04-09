package test.java.com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.*;

public class CheckoutTest extends BaseTest {

    @Test
    public void goodInfo() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();
        inventoryPage.getCartImageLink().click();
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");

        informationPage.getContinueButton().click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assertions.assertEquals(checkoutPage.getUrl(), driver.getCurrentUrl());
    }

    @Test
    public void completeCheckout() {
        HomePage homePage = new HomePage(driver);
        driver.get(homePage.getUrl());
        homePage.getUsernameElement().sendKeys("standard_user");
        homePage.getPasswordElement().sendKeys("secret_sauce");
        homePage.getSubmitElement().click();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();
        inventoryPage.getCartImageLink().click();
        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");
        informationPage.getContinueButton().click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.getFinishButton().click();

        FinishPage finishPage = new FinishPage(driver);
        Assertions.assertEquals(FinishPage.getUrl(), driver.getCurrentUrl());
        Assertions.assertTrue(finishPage.getCompleteElement().getText().contains("Your order has been dispatched"));
    }
}
