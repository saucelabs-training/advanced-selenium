package test.examples;

import test.base.Base;
import test.pages.HomePage;
import test.pages.InventoryPage;
import test.data.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeclarativeTest extends Base {

    @Test
    @DisplayName("Imperative Test with Page Objects")
    void declarative() {
        HomePage homePage = HomePage.visit(driver);
        User user = User.valid();

        homePage.signIn(user);

        InventoryPage inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.onPage());
    }
}
