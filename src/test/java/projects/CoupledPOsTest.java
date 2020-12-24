package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.LocalTestBase;
import test.java.pages.*;

public class CoupledPOsTest extends LocalTestBase {

    @Test
    public void decoupled() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.selectProduct("Sauce Labs Fleece Jacket");
        inventoryPage.navigateToShoppingCart();

        new ShoppingCartPage().checkOut();
        new InformationPage().addInformation();
        new OverviewPage().finish();

        Assert.assertTrue(new CompletePage().isSuccessful());
    }
}
