package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.data.Person;
import test.java.element.ButtonElement;
import test.java.element.Element;
import test.java.element.TextElement;
import test.java.exceptions.PageValidationException;

import java.util.function.Function;

public class InformationPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/checkout-step-one.html";

    protected TextElement firstName = browser.textField("First Name Field", By.id("first-name"));
    protected TextElement lastName = browser.textField("Last Name Field", By.id("last-name"));
    protected TextElement postalCode = browser.textField("Postal Code Field", By.id("postal-code"));
    private ButtonElement submitButton = browser.button("Submit Button", By.className("cart_button"));
    private Element errorMessage = browser.element("Error Message", By.cssSelector("[data-test=error]"));

    public static InformationPage visit() {
        InformationPage informationPage = new InformationPage();
        driver.navigate().to(URL);
        return informationPage;
    }

    public void validateSuccessfulInformationAddition() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> informationAddSuccessful());
        } catch (TimeoutException e) {
            String message = errorMessage.getText();
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
        fillForm(person);
        submitButton.click();
        validateSuccessfulInformationAddition();
    }

    public Person addInformationSuccessfully() {
        Person person = new Person();
        addInformationSuccessfully(person);
        return person;
    }
}
