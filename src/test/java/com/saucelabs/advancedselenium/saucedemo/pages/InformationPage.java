package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

public class InformationPage extends BasePage {
    private final By firstNameElement = By.cssSelector("input[data-test='firstName']");
    private final By lastNameElement = By.cssSelector("input[data-test='lastName']");
    private final By postalCodeElement = By.cssSelector("input[data-test='postalCode']");
    private final By continueButton = By.cssSelector("input[data-test='continue']");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public InformationPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void addInformationSuccessfully(String firstName, String lastName, String postalCode) {
        driver.findElement(firstNameElement).sendKeys(firstName);
        driver.findElement(lastNameElement).sendKeys(lastName);
        driver.findElement(postalCodeElement).sendKeys(postalCode);
        driver.findElement(continueButton).click();

        validateInformationAdded();
    }

    private void validateInformationAdded() {
        if (!CheckoutPage.URL.equals(driver.getCurrentUrl())) {
            List<WebElement> errors = driver.findElements(errorElement);
            String additional = errors.isEmpty() ? "" : " found error: " + errors.get(0).getText();
            throw new PageValidationException("Information not submitted;" + additional);
        }
    }
}
