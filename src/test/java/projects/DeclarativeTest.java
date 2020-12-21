package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.data.User;
import test.java.pages.InventoryPage;
import test.java.pages.SauceDemoPage;

public class DeclarativeTest extends SauceTestBase {

    @Test
    public void login() {
        SauceDemoPage sauceDemoPage = SauceDemoPage.visit();
        sauceDemoPage.login(User.valid());

        InventoryPage inventoryPage = new InventoryPage();
        Assert.assertTrue(inventoryPage.onPage());
    }
}
