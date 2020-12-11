package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class XPathAdvancedTest extends SauceTestBase {

    @Test
    public void textContent() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the "Add User Header"
        WebElement element = driver.findElement(By.xpath(".//*[normalize-space()='Add user']"));

        Assert.assertTrue(validHeader(element));
    }

    @Test
    public void textPartialContent() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the "Add User Header"
        WebElement element = driver.findElement(By.xpath("//*[text()[contains(.,'user')]]"));

        Assert.assertTrue(validHeader(element));
    }

    @Test
    public void previousSibling() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Dental Records Input Element from HTML5 Email Text Field"
        WebElement element = driver.findElement(By.xpath("//*[@id='html5_email']/preceding::*[4]"));

        Assert.assertTrue(validDentalUpload(element));
    }

    @Test
    public void parent() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Add User Form from child"
        WebElement element = driver.findElement(By.xpath("//*[@label='nordic']/ancestor::*[1]"));

        Assert.assertTrue(validCountrySelect(element));
    }

    @Test
    public void greatGrandparent() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Add User Form from child"
        WebElement element = driver.findElement(By.xpath("//*[@label='nordic']/ancestor::*[3]"));

        Assert.assertTrue(validNewUserForm(element));
    }

    @Test
    public void ancestorTag() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Add User Form from child"
        WebElement element = driver.findElement(By.xpath("//*[@label='nordic']/ancestor::form[1]"));

        Assert.assertTrue(validNewUserForm(element));
    }






    // Ignore The following validations:

    private boolean validHeader(WebElement element) {
        return "h2".equals(element.getTagName());
    }

    private boolean validCountrySelect(WebElement element) {
        return "new_user_country".equals(element.getAttribute("id"));
    }

    private boolean validNewUserForm(WebElement element) {
        return "new_user".equals(element.getAttribute("id"));
    }

    private boolean validDentalUpload(WebElement element) {
        return "new_user_teeth".equals(element.getAttribute("id"));
    }



}
