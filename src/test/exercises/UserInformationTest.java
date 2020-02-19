package test.exercises;

import org.junit.jupiter.api.Assertions;
import test.base.Base;
import test.data.Person;
import test.pages.CartPage;
import test.pages.InformationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserInformationTest extends Base {
    @Test
    @DisplayName("User Information Accepted")
    public void fillUserInformation() {
        InformationPage page = InformationPage.visit(driver);

        // Use the Beverly Hills Person
        Person person = Person.beverlyHills();

        page.provideInformation(person);

        // Assert on the Cart Page
        CartPage cartPage = new CartPage(driver);
        Assertions.assertTrue(cartPage.onPage());
    }
}
