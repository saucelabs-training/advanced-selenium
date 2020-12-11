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

        String cssDescendant = "#checkout_info_container #postal-code";
        String cssDirectChild = ".checkout_info > #postal-code";
        String cssLastChild = "input:last-child";
        String cssChildFromEnd = "input:nth-last-child(1)";
        String cssChildFromBeginning = "input:nth-child(3)";
        String cssAttributeStartsWith = "[data-test^=post]";
        String cssAttributeEndsWith = "[data-test$=ode]";
        String cssAttributeContains = "[data-test*=alC]";
        String cssNextSibling = "[data-test=lastName] + [data-test=postalCode]";
        String cssSubsequentSibling = "[data-test=firstName] ~ [data-test=postalCode]";




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