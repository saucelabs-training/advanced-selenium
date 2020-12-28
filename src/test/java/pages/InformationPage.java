package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.data.Person;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class InformationPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/checkout-step-one.html";

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By SUBMIT = By.className("cart_button");
    private static final By ERROR = By.cssSelector("[data-test=error]");

    public static InformationPage visit() {
        InformationPage informationPage = new InformationPage();
        driver.navigate().to(URL);
        return informationPage;
    }

    public void submitInformation(String first, String last, String postal) {
        locateDisplayedElement("First Name", FIRST_NAME).sendKeys(first);
        locateDisplayedElement("Last Name", LAST_NAME).sendKeys(last);
        locateDisplayedElement("Postal Code", POSTAL_CODE).sendKeys(postal);
        locateDisplayedElement("Submit Button", SUBMIT).click();
    }

    public void addInformationSuccessfully(String first, String last, String postal) {
        submitInformation(first, last, postal);
        validateSuccessfulInformationAddition();
    }

    public void validateSuccessfulInformationAddition() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> informationAddSuccessful());
        } catch (TimeoutException e) {
            String message = driver.findElement(ERROR).getText();
            throw new PageValidationException("Information was not successful after 5 seconds: " + message);
        }
    }

    public boolean informationAddSuccessful() {
        return !driver.getCurrentUrl().equals(URL);
    }

    public void addInformation() {
        // Don't use this one
    }

    public void addInformationSuccessfully(Person person) {
        addInformationSuccessfully(person.getFirstName(), person.getLastName(), person.getPostalCode());
        validateSuccessfulInformationAddition();
    }

    public Person addInformationSuccessfully() {
        Person person = new Person();
        addInformationSuccessfully(person);
        return person;
    }
}
