package test.java.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.java.SauceTestBase;

public class ElementActionsClassTest extends SauceTestBase {

    @Test
    public void clickAction() {
        driver.get("http://a.testaddressbook.com");

        // Actions Class
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(By.id("sign-in"));

        action.click(element).build().perform();
    }

    @Test
    public void actionExamples() {
        driver.get("http://a.testaddressbook.com");

        // Actions Examples
        Actions action = new Actions(driver);

        WebElement element = driver.findElement(By.id("sign-in"));
        WebElement element2 = driver.findElement(By.tagName("a"));

        // Hover
        action.moveToElement(element).build().perform();

        // Drag & Drop
        action.dragAndDrop(element, element2).build().perform();

        // click - pause - release
        action.clickAndHold(element).pause(100).release().build().perform();
    }
}