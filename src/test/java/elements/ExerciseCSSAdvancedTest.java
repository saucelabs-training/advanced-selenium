package test.java.elements;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

//
// Exercise:
// Fill in the Strings to Define the Specified Locators to get the tests to pass
//


public class ExerciseCSSAdvancedTest extends SauceTestBase {

    @Before
    public void navigate() {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
    }

    @Test
    public void locateZipCodeField() {

        String cssDescendant = "";
        String cssDirectChild = "";
        String cssLastChild = "";
        String cssChildFromEnd = "";
        String cssChildFromBeginning = "";
        String cssAttributeStartsWith = "";
        String cssAttributeEndsWith = "";
        String cssAttributeContains = "";
        String cssNextSibling = "";
        String cssSubsequentSibling = "";




        // IGNORE THE DETAILS OF THE ASSERTIONS BELOW
        // ALL YOU NEED TO KNOW IS THAT IF YOU RUN THIS TEST
        // WITH THE RIGHT VALUES ABOVE IT WILL PASS

        WebElement zipCode = driver.findElement(By.id("postal-code"));

        assertEquals(zipCode, driver.findElement(By.cssSelector(cssDescendant)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssDirectChild)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssLastChild)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssChildFromEnd)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssChildFromBeginning)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssAttributeStartsWith)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssAttributeEndsWith)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssAttributeContains)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssNextSibling)));
        assertEquals(zipCode, driver.findElement(By.cssSelector(cssSubsequentSibling)));

        assertTrue(cssDescendant.contains(" "));
        assertTrue(cssDirectChild.contains(" > "));
        assertTrue(cssLastChild.contains(":last"));
        assertTrue(cssChildFromEnd.contains(":nth-l"));
        assertTrue(cssChildFromBeginning.contains(":nth-c"));
        assertTrue(cssAttributeStartsWith.contains("^="));
        assertTrue(cssAttributeEndsWith.contains("$="));
        assertTrue(cssAttributeContains.contains("*="));
        assertTrue(cssNextSibling.contains(" + "));
        assertTrue(cssSubsequentSibling.contains(" ~ "));
    }
}