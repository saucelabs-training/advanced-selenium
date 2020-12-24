package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.pages.*;

public class CoupledPOsTest extends SauceTestBase {

    @Test
    public void decoupled() {
        HomePage homePage = new HomePage();
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        inventoryPage.selectProduct("Sauce Labs Bolt T-Shirt");
        inventoryPage.selectProduct("Sauce Labs Fleece Jacket");
        inventoryPage.navigateToShoppingCart();

        new ShoppingCartPage().checkOut();
        new InformationPage().addInformation();
        new OverviewPage().finish();

        Assert.assertTrue(new CompletePage().isSuccessful());
    }
}
