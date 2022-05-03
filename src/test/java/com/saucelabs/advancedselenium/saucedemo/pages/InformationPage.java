package com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.data.Person;
import com.saucelabs.advancedselenium.saucedemo.elements.Element;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;

public class InformationPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-one.html";
    private final Element firstNameElement = browser.getElement(By.cssSelector("input[data-test='firstName']"));
    private final Element lastNameElement = browser.getElement(By.cssSelector("input[data-test='lastName']"));
    private final Element postalCodeElement = browser.getElement(By.cssSelector("input[data-test='postalCode']"));
    private final Element continueButton = browser.getElement(By.cssSelector("input[data-test='continue']"));
    private final ElementList errorElements = browser.getElements(By.cssSelector("[data-test=error]"));

    public InformationPage(Browser browser) {
        super(browser);
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
            browser.waitUntil(() -> !URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            String additional = errorElements.isEmpty() ? "" : " found error: " + errorElements.getFirst().getText();
            throw new PageValidationException("Information not submitted;" + additional);
        }
    }
}
