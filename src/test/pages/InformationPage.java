package test.pages;

import org.openqa.selenium.WebDriver;
import test.data.Person;

public class InformationPage {

    private WebDriver driver;

    // Declare the Elements

    // Implement Visit Method
    public static InformationPage visit(WebDriver driver) {
        return new InformationPage(driver);
    }

    public InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Implement filling out the form
    public void provideInformation(Person person) {
    }
}
