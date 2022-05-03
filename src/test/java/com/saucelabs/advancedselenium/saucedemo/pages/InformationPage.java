package com.saucelabs.advancedselenium.saucedemo.pages;

import com.saucelabs.advancedselenium.saucedemo.SauceDemoApp;
import org.openqa.selenium.TimeoutException;
import com.saucelabs.advancedselenium.saucedemo.data.Person;
import com.saucelabs.advancedselenium.saucedemo.elements.Button;
import com.saucelabs.advancedselenium.saucedemo.elements.ElementList;
import com.saucelabs.advancedselenium.saucedemo.elements.TextField;

public class InformationPage extends BasePage {
    public static final String URL = "https://www.saucedemo.com/checkout-step-one.html";
    private final TextField firstName = browser.getTextField("firstName");
    private final TextField lastName = browser.getTextField("lastName");
    private final TextField postalCode = browser.getTextField("postalCode");
    private final Button submit = browser.getButton("continue");
    private final ElementList errorElements = browser.getElements("error");

    public InformationPage(SauceDemoApp app) {
        super(app);
    }

    public void addInformationSuccessfully() {
        addInformationSuccessfully(new Person());
    }

    public void addInformationSuccessfully(Person person) {
        submitForm(person);

        try {
            browser.waitUntil(() -> !URL.equals(browser.getCurrentUrl()));
        } catch (TimeoutException ex) {
            String additional = errorElements.isEmpty() ? "" : " found error: " + errorElements.getFirst().getText();
            throw new PageValidationException("Information not submitted;" + additional);
        }
    }

    public InformationPage visit() {
        browser.get(URL);
        return this;
    }
}
