package test.exercises;

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
        Person person = new Person();

        page.provideInformation(person);

        // Assert on the Cart Page

    }
}
