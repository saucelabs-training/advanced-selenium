package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.elements.TextFieldElement;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.Person;

import java.util.function.Function;

public class InformationPage extends BasePage {
    private final TextFieldElement firstName = getTextField(By.id("first-name"));
    private final TextFieldElement lastName = getTextField(By.id("last-name"));
    private final TextFieldElement postalCode = getTextField(By.id("postal-code"));
    private final ButtonElement continueButton = getButton(By.id("continue"));
    private final Element errorElement = getElement(By.cssSelector("[data-test=error]"), "Error Element");

    public InformationPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-step-one.html";
    }

    public void submitInfoSuccessfully() {
        submitInfoSuccessfully(new Person());
    }

    public void submitInfoSuccessfully(Person person) {
        submitForm(person);
        try {
            wait.until((Function<WebDriver, Object>) driver -> !isOnPage());
        } catch (TimeoutException ex) {
            String error = errorElement.getText();
            throw new PageValidationException("Information submission was not successful: " + error);
        }
    }

    private void submitForm(Person person) {
        fillForm(person);
        continueButton.click();
    }
}
