package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.data.Person;

import java.util.function.Function;

public class InformationPage extends BasePage {
    private final By firstNameTextField = By.id("first-name");
    private final By lastNameTextField = By.id("last-name");
    private final By postalCodeTextField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorElement = By.cssSelector("[data-test=error]");

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
            String error = driver.findElement(errorElement).getText();
            throw new PageValidationException("Information submission was not successful: " + error);
        }
    }

    private void submitForm(Person person) {
        getElement("firstNameTextField").sendKeys(person.getFirstName());
        getElement("lastNameTextField").sendKeys(person.getLastName());
        getElement("postalCodeTextField").sendKeys(person.getPostalCode());
        getElement("continueButton").click();
    }
}
