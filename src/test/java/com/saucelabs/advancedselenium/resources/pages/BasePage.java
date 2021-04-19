package test.java.com.saucelabs.advancedselenium.resources.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.saucelabs.advancedselenium.resources.data.DataModel;
import test.java.com.saucelabs.advancedselenium.resources.elements.ButtonElement;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.elements.TextFieldElement;

import java.lang.reflect.Field;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

public abstract class BasePage {
    public Duration defaultWaitTime = Duration.ofSeconds(20);
    protected RemoteWebDriver driver;
    protected String pageUrl;
    protected WebDriverWait wait;
    private Set<Field> elements = new HashSet<>();

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, defaultWaitTime);

        for (Field field : this.getClass().getDeclaredFields()) {
            if (Element.class.isAssignableFrom(field.getType())) {
                elements.add(field);
            }
        }
    }

    public RemoteWebDriver getDriver() {
        return driver;
    }

    public void visit() {
        driver.get(pageUrl);
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals(pageUrl);
    }

    public void fillForm(DataModel data) {
        for (Field field : elements) {
            if (field.getType().equals(TextFieldElement.class)) {
                field.setAccessible(true);
                String dataValue = (String) data.getValue(field.getName());
                try {
                    ((TextFieldElement) field.get(this)).sendKeys(dataValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Element getElement(By locator, String description) {
        return new Element(locator, description, this);
    }

    public ButtonElement getButton(By locator) {
        return new ButtonElement(locator, this);
    }

    public ButtonElement getButton(By locator, String description) {
        return new ButtonElement(locator, description, this);
    }

    public TextFieldElement getTextField(By locator) {
        return new TextFieldElement(locator, this);
    }

    public TextFieldElement getTextField(By locator, String description) {
        return new TextFieldElement(locator, description, this);
    }
}
