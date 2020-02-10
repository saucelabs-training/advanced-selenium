package test.exercises;

import test.base.Base;
import test.pages.HomePage;
import test.pages.InventoryPage;
import test.data.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeclarativeTest extends Base {

    @Test
    @DisplayName("Imperative Example of Locked Out User")
    void lockedOutUserCanNotLogInImperative() {
        driver.get("https://www.saucedemo.com/");

        String username = "locked_out_user";
        String password = "secret_sauce";

        HomePage homePage = new HomePage(driver);
        homePage.getUsername().sendKeys(username);
        homePage.getPassword().sendKeys(password);
        homePage.getSubmit().click();

        assertNotEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Declarative Example of Locked Out User")
    void lockedOutUserCanNotLogInDeclarative() {
    }
}
