package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.pages.HomePage;
import test.java.pages.InventoryPage;

public class AtomicTest extends SauceTestBase {

    @Test
    public void login() {
        HomePage homePage = HomePage.visit();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage();
        Assert.assertTrue(inventoryPage.isOnPage());
    }
}
