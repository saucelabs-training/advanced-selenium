package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.advancedselenium.saucedemo.data.Person;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;

import java.util.function.Function;

public class InformationPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-one.html";
    private final Element firstNameElement = new Element(driver, By.cssSelector("input[data-test='firstName']"));
    private final Element lastNameElement = new Element(driver, By.cssSelector("input[data-test='lastName']"));
    private final Element postalCodeElement = new Element(driver, By.cssSelector("input[data-test='postalCode']"));
    private final Element continueButton = new Element(driver, By.cssSelector("input[data-test='continue']"));
    private final ElementList errorElements = new ElementList(driver, By.cssSelector("[data-test=error]"));

    public InformationPage(RemoteWebDriver driver) {
        super(driver);
    }

    public void addInformationSuccessfully() {
        addInformationSuccessfully(new Person());
    }

    public void addInformationSuccessfully(Person person) {
        firstNameElement.sendKeys(person.getFirstName());
        lastNameElement.sendKeys(person.getLastName());
        postalCodeElement.sendKeys(person.getPostalCode());
        continueButton.click();

        try {
            wait.until((Function<WebDriver, Object>) driver -> !URL.equals(driver.getCurrentUrl()));
        } catch (TimeoutException ex) {
            String additional = errorElements.isEmpty() ? "" : " found error: " + errorElements.getFirst().getText();
            throw new PageValidationException("Information not submitted;" + additional);
        }
    }
}
