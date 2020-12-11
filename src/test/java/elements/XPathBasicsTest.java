package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class XPathBasicsTest extends SauceTestBase {

    @Test
    public void forTagName() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Button 2 Button by Tag Name
        WebElement element = driver.findElement(By.xpath(".//*[local-name()='button']"));

        Assert.assertTrue(validSubmit2Button(element));
    }

    @Test
    public void forAttributePresence() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Email Address Text Field
        WebElement element = driver.findElement(By.xpath(".//*[@contenteditable]"));

        Assert.assertTrue(validEmailField(element));
    }

    @Test
    public void forAttributeValue() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Cars Checkbox using data- attribute
        WebElement element = driver.findElement(By.xpath(".//*[@data-locator='cars']"));

        Assert.assertTrue(validCarsCheckbox(element));
    }

    @Test
    public void forMultipleAttributeValues() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Dentistry Checkbox using multiple attributes
        WebElement element = driver.findElement(By.xpath(".//*[@value='dentistry' and @disabled='disabled']"));

        Assert.assertTrue(validDentistCheckbox(element));
    }

    @Test
    public void forClass() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Country Select List by class Name
        WebElement element = driver.findElement(By.xpath(".//*[contains(concat(' ', @class, ' '), ' country ')]"));

        Assert.assertTrue(validCountrySelect(element));
    }

    @Test
    public void forMultipleClasses() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Element with id "messages" using multiple class names
        String locator = ".//*[contains(concat(' ', @class, ' '), ' multiple ') and contains(concat(' ', @class, ' '), ' here ')]";
        WebElement element = driver.findElement(By.xpath(locator));

        Assert.assertTrue(validMultipleOne(element));
    }

    @Test
    public void forMultipleLocators() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Dentistry Checkbox using multiple attributes
        String locator = ".//*[local-name()='div'][contains(concat(' ', @class, ' '), ' multiple ') and " +
                "contains(concat(' ', @class, ' '), ' classes ')][@random='foo' and @data-locator='first div' and @id]";
        WebElement element = driver.findElement(By.xpath(locator));

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
