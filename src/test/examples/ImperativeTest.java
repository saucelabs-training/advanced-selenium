package test.examples;

import test.base.Base;
import test.pages.HomePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImperativeTest extends Base {

    @Test
    @DisplayName("Imperative Test with Page Objects")
    void imperativePageObjects() {
        driver.get("https://www.saucedemo.com/");

        String username = "standard_user";
        String password = "secret_sauce";

        HomePage homePage = new HomePage(driver);
        homePage.getUsername().sendKeys(username);
        homePage.getPassword().sendKeys(password);
        homePage.getSubmit().click();

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}
