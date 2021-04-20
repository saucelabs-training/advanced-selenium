package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

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

    public void submitForm(String firstName, String lastName, String postalCode) {
        getElement("firstNameTextField").sendKeys(firstName);
        getElement("lastNameTextField").sendKeys(lastName);
        getElement("postalCodeTextField").sendKeys(postalCode);
        getElement("continueButton").click();
    }

    public void validateSubmitSuccessful() {
        if (isOnPage()) {
            String error = getElement("errorElement").getText();
            throw new PageValidationException("Information submission was not successful: " + error);
        }
    }
}
