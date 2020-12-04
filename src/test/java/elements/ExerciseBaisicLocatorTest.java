package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class ExerciseBaisicLocatorTest extends SauceTestBase {

    @Test
    public void usuallyUnique() {
        driver.get("https://www.saucedemo.com/");

        // Locate User Name Text Field

        // ID
        WebElement element1 = driver.findElement(By.id(""));

        // Name
        WebElement element2 = driver.findElement(By.name(""));


        Assert.assertTrue(validPassword(element1));
        Assert.assertTrue(validPassword(element2));
    }

    @Test
    public void usuallyBad() {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Locate Black T-Shirt Link

        // Link Text
        WebElement element1 = driver.findElement(By.linkText(""));

        // Partial Link Text
        WebElement element2 = driver.findElement(By.partialLinkText(""));


        Assert.assertTrue(validBlackShirt(element1));
        Assert.assertTrue(validBlackShirt(element2));
    }

    @Test
    public void usuallyNotUnique() {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Locate Element For Adding Bike Light to the cart

        // Class Name
        WebElement element1 = driver.findElements(By.className("")).get(0);

        // Tag Name
        WebElement element2 = driver.findElements(By.tagName("")).get(0);


        Assert.assertTrue(validLight(element1));
        Assert.assertTrue(validLight(element2));
    }


    // Ignore The following validations:

    private boolean validPassword(WebElement element) {
        return "Password".equals(element.getAttribute("placeholder"));
    }

    private boolean validBlackShirt(WebElement element) {
        return "item_1_title_link".equals(element.getAttribute("id"));
    }

    private boolean validLight(WebElement element1) {
        WebElement element2 = driver.findElement(By.cssSelector("div.inventory_item:nth-child(2) button"));
        return element1.equals(element2);
    }
}
