package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class ExerciseCSSBasicsTest extends SauceTestBase {

    @Test
    public void forIDs() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the Reset Button by ID
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validResetButton(element));
    }

    @Test
    public void forClass() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Developer Text Field by class Name
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validDeveloperField(element));
    }

    @Test
    public void forMultipleClasses() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Element with id "there" using multiple class names
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validMultipleTwo(element));
    }

    @Test
    public void forTagName() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate the New User Form by Tag Name
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validNewUserForm(element));
    }

    @Test
    public void forAttribute() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Developer Text Field by data- attribute
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validDeveloperField(element));
    }

    @Test
    public void forMultipleAttributes() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Personal Code Text Field with Multiple Attributes
        WebElement element = driver.findElement(By.cssSelector(""));

        Assert.assertTrue(validPersonalCode(element));
    }

    @Test
    public void forMultipleLocators() {
        driver.get("http://watir.com/examples/forms_with_input_elements.html");

        // Locate Portrait file upload button with multiple locators (id, class, name attribute & title attribute
        String locator = "";
        WebElement element = driver.findElement(By.cssSelector(locator));

        Assert.assertTrue(validPortraitInput(element));
    }


    // Ignore The following validations:

    private boolean validResetButton(WebElement element) {
        return "Reset".equals(element.getAttribute("value"));
    }

    private boolean validDeveloperField(WebElement element) {
        return "Developer".equals(element.getAttribute("value"));
    }

    private boolean validMultipleTwo(WebElement element) {
        return "there".equals(element.getAttribute("id"));
    }

    private boolean validNewUserForm(WebElement element) {
        return "post_to_me".equals(element.getAttribute("action"));
    }

    private boolean validPortraitInput(WebElement element) {
        return "file".equals(element.getAttribute("type"));
    }

    private boolean validPersonalCode(WebElement element) {
        return "new_user_code".equals(element.getAttribute("id"));
    }
}

