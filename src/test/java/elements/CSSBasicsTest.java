package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class CSSBasicsTest extends SauceTestBase {

    @Test
    public void forIDs() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Submit Button by ID
        WebElement element = driver.findElement(By.cssSelector("#new_user_submit"));

        Assert.assertTrue(validSubmitButton(element));
    }

    @Test
    public void forClass() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Country Select List by class Name
        WebElement element = driver.findElement(By.cssSelector(".country"));

        Assert.assertTrue(validCountrySelect(element));
    }

    @Test
    public void forMultipleClasses() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Element with id "messages" using multiple class names
        WebElement element = driver.findElement(By.cssSelector(".multiple.here"));

        Assert.assertTrue(validMultipleOne(element));
    }

    @Test
    public void forTagName() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Button 2 Button by Tag Name
        WebElement element = driver.findElement(By.cssSelector("button"));

        Assert.assertTrue(validSubmit2Button(element));
    }

    @Test
    public void forAttributePresence() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Email Address Text Field
        WebElement element = driver.findElement(By.cssSelector("[contenteditable]"));

        Assert.assertTrue(validEmailField(element));
    }

    @Test
    public void forAttributeValue() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Cars Checkbox using data- attribute
        WebElement element = driver.findElement(By.cssSelector("[data-locator=cars]"));

        Assert.assertTrue(validCarsCheckbox(element));
    }

    @Test
    public void forMultipleAttributeValues() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Dentistry Checkbox using multiple attributes
        WebElement element = driver.findElement(By.cssSelector("[value=dentistry][disabled=disabled]"));

        Assert.assertTrue(validDentistCheckbox(element));
    }

    @Test
    public void forMultipleLocators() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Dentistry Checkbox using multiple attributes
        String locator = "div#messages.multiple.classes.here[random=foo][data-locator='first div'][id]";
        WebElement element = driver.findElement(By.cssSelector(locator));

        Assert.assertTrue(validMultipleOne(element));
    }


    // Ignore The following validations:

    private boolean validSubmitButton(WebElement element) {
        return "Submit".equals(element.getAttribute("value"));
    }

    private boolean validCountrySelect(WebElement element) {
        return "new_user_country".equals(element.getAttribute("name"));
    }

    private boolean validMultipleOne(WebElement element) {
        return driver.findElement(By.tagName("div")).equals(element);
    }

    private boolean validSubmit2Button(WebElement element) {
        return "button_2".equals(element.getAttribute("value"));
    }

    private boolean validCarsCheckbox(WebElement element) {
        return "cars".equals(element.getAttribute("value"));
    }

    private boolean validDentistCheckbox(WebElement element) {
        return "new_user_interests_dentistry".equals(element.getAttribute("id"));
    }

    private boolean validEmailField(WebElement element) {
        return "new_user_email".equals(element.getAttribute("id"));
    }
}

