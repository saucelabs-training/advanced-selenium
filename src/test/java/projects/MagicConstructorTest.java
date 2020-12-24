package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import test.java.SauceTestBase;
import test.java.pages.HomePage;
import test.java.pages.MagicInventoryPage;
import test.java.pages.ShoppingCartPage;

public class MagicConstructorTest extends SauceTestBase {

    @Test
    public void autoLogin() {
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();
        Assert.assertTrue(magicInventoryPage.isOnPage());
    }

    @Test
    public void moveToUnexpectedStateProblem() {
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();
        driver.navigate().back();

        // Fails at its purpose of enforcing state
        magicInventoryPage.shoppingCart();
        Assert.assertTrue(new ShoppingCartPage().isOnPage());
    }

    @Test
    public void assumesKnownStateProblem() {
        HomePage homePage = new HomePage();
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        // Shouldn't need to log in again to use this page object
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();
        magicInventoryPage.shoppingCart();
        Assert.assertTrue(new ShoppingCartPage().isOnPage());
    }

    @Test
    public void visitImperative() {
        MagicInventoryPage magicInventoryPage = MagicInventoryPage.visitImperative();
        magicInventoryPage.shoppingCart();
        Assert.assertTrue(new ShoppingCartPage().isOnPage());
    }

    @Test
    public void visitReferential() {
        MagicInventoryPage magicInventoryPage = MagicInventoryPage.visitReferential();
        magicInventoryPage.shoppingCart();
        Assert.assertTrue(new ShoppingCartPage().isOnPage());
    }

    @Test
    public void visitDirect() {
        MagicInventoryPage magicInventoryPage = MagicInventoryPage.visitDirect();
        magicInventoryPage.shoppingCart();
        Assert.assertTrue(new ShoppingCartPage().isOnPage());
    }
}
