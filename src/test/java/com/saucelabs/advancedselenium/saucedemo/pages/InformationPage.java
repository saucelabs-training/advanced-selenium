package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InformationPage extends BasePage<InformationPage> {
    private final By firstNameTextField = By.id("first-name");
    private final By lastNameTextField = By.id("last-name");
    private final By postalCodeTextField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public InformationPage(RemoteWebDriver driver) {
        super(driver);
    }

    public CheckoutPage submitForm(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameTextField).sendKeys(firstName);
        driver.findElement(lastNameTextField).sendKeys(lastName);
        driver.findElement(postalCodeTextField).sendKeys(postalCode);
        driver.findElement(continueButton).click();
        return new CheckoutPage(driver);
    }
}
