package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.LocalTestBase;

import java.util.List;
import java.util.ListIterator;

@SuppressWarnings("SameParameterValue")
public class AdvancedLocateElementTest extends LocalTestBase {

    @Test
    public void usingXPath() {
        driver.get("https://www.saucedemo.com/inventory.html");

        String xpath = ".//div[normalize-space()='Sauce Labs Onesie']/ancestor::*[3]//button";
        WebElement element = driver.findElement(By.xpath(xpath));

        // Obviousness Check: Bad
        //      Ancestor & Button are not obvious what is happening
        // Brittleness Check: Bad
        //      This will work if elements are sorted differently, or moved around
        //      What if the text changes (prefer attribute value); What if it's multi-language
        //      But adding any additional divs or elements will break the ancestor::*[3]
        // Efficiency: Good
        //      Only one command

        Assert.assertTrue(validAddOnesie(element));
    }

    @Test
    public void hardCodeIndex() {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Get the Price Bar Div for the Onesie based on index
        WebElement parentElement = driver.findElements(By.className("pricebar")).get(4);

        // Find the (only) button element nested under the pricebar
        WebElement element = parentElement.findElement(By.tagName("button"));

        // Obviousness Check: Good
        //      "Find the button under the 5th element with a given price bar"
        // Brittleness Check: Poor
        //      Adding content or sorting will prevent this from being "the fifth one"
        // Efficiency: Good
        //      Only Two commands

        Assert.assertTrue(validAddOnesie(element));
    }

    @Test
    public void dynamicIndexByText() throws Exception {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Determine which index of the specific element to contain the text
        Integer index = getIndexByString(By.className("inventory_item_name"), "Sauce Labs Onesie");

        // Get the container item by index
        WebElement parentElement = driver.findElements(By.className("inventory_item")).get(index);

        // Find the (only) button element nested in that container
        WebElement element = parentElement.findElement(By.tagName("button"));

        // Obviousness Check: Good
        //      "Which index is the onesie, and use that to find the parent of the button, then the button"
        //      It's using "index" instead of "elements" which is slightly less obvious
        // Brittleness Check: Good
        //      It's using text value which is more brittle than attribute values
        //      It still works if more things are added or things are sorted
        //      It using specific element class names; less likely that a minor change in DOM will affect it
        // Efficiency Check: Ok
        //      It iterates through a find elements to match text

        Assert.assertTrue(validAddOnesie(element));
    }

    @Test
    public void dynamicElementByText() throws Exception {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Determine which container contains the specific text
        WebElement parent = partialStringMatch(By.className("inventory_item"), "Sauce Labs Onesie");

        // Find the (only) button element nested in that container
        WebElement element = parent.findElement(By.tagName("button"));

        // Obviousness Check: Good
        //      Strings, Class Names and Hierarchy all obvious
        // Brittleness Check: Good
        //      Text is less brittle than Attributes
        //      This still works if we sort or add new products
        //      Adding elements inside inventory_item element won't affect things
        // Efficiency: Ok
        //      Iterate once through element list

        Assert.assertTrue(validAddOnesie(element));
    }


    private Integer getIndexByString(By locator, String string) throws Exception {
        List<WebElement> elements = driver.findElements(locator);
        ListIterator<WebElement> iter = elements.listIterator();

        while (iter.hasNext()) {
            int currentIndex = iter.nextIndex();
            if (string.equals(iter.next().getText())) {
                return currentIndex;
            }
        }
        throw new Exception("Can not find an element matching " + string + "with the locator " + locator.toString());
    }

    private WebElement partialStringMatch(By locator, String string) throws Exception {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements){
            if(element.getText().contains(string)) {
                return element;
            }
        }
        throw new Exception("Can not find an element matching " + string + "with the locator " + locator.toString());
    }


    // Ignore The following validations:

    private boolean validAddOnesie(WebElement element1) {
        WebElement element2 = driver.findElement(By.cssSelector("div.inventory_item:nth-child(5) button"));
        return element1.equals(element2);
    }
}

