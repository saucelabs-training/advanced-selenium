package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.LocalTestBase;
import test.java.pages.HomePage;
import test.java.pages.InventoryPage;

public class AtomicTest extends LocalTestBase {

    @Test
    public void login() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isOnPage());
    }
}
