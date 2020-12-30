package test.java.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.data.DataModel;
import test.java.element.Browser;
import test.java.element.Element;
import test.java.element.TextElement;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class BasePage {
    protected static Browser browser;
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static final int DEFAULT_WAIT_TIME = 20;
    private Set<Field> elements = new HashSet<Field>();

    public static Browser getBrowser() {
        return browser;
    }

    public static void createBrowser(RemoteWebDriver driver) {
        browser = new Browser(driver);
        BasePage.driver = browser.getDriver();
        wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    public BasePage() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (Element.class.isAssignableFrom(field.getType())) {
                elements.add(field);
            }
        }
    }

    public void fillForm(DataModel data) {
        for (Field field : elements) {
            if (field.getType().equals(TextElement.class)) {
                field.setAccessible(true);
                String dataValue = (String) data.getValue(field.getName());
                try {
                    ((TextElement) field.get(this)).sendKeys(dataValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

