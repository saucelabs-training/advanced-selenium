package test.java.elements;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class LocateElementTest extends SauceTestBase {

    @Test
    public void usuallyUnique() {
        driver.get("https://www.saucedemo.com/");

        // Locate User Name Text Field

        // ID
        WebElement element1 = driver.findElement(By.id("user-name"));

        // Name
        WebElement element2 = driver.findElement(By.name("user-name"));


        Assert.assertTrue(validUsername(element1));
        Assert.assertTrue(validUsername(element2));
    }

    @Test
    public void usuallyBad() {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Locate Sauce Labs Backpack Link

        // Link Text
        WebElement element1 = driver.findElement(By.linkText("Sauce Labs Backpack"));

        // Partial Link Text
        WebElement element2 = driver.findElement(By.partialLinkText("Backpack"));


        Assert.assertTrue(validBackpack(element1));
        Assert.assertTrue(validBackpack(element2));
    }

    @Test
    public void usuallyNotUnique() {
        driver.get("https://www.saucedemo.com/inventory.html");

        // Locate Element For Adding Onesie to the cart

        // Class Name
        WebElement element1 = driver.findElements(By.className("btn_inventory")).get(4);

        // Tag Name
        WebElement element2 = driver.findElements(By.tagName("button")).get(6);


        Assert.assertTrue(validAddOnesie(element1));
        Assert.assertTrue(validAddOnesie(element2));
    }


    // Ignore The following validations:

    private boolean validUsername(WebElement element) {
        return "Username".equals(element.getAttribute("Placeholder"));
    }

    private boolean validBackpack(WebElement element) {
        return "item_4_title_link".equals(element.getAttribute("id"));
    }

    private boolean validAddOnesie(WebElement element1) {
        WebElement element2 = driver.findElement(By.cssSelector("div.inventory_item:nth-child(5) button"));
        return element1.equals(element2);
    }


}

