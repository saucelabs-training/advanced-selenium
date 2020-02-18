package test.examples;

import test.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ActionsClass extends Base {

    @Test
    @DisplayName("Example Code for Using Actions Class")
    public void actionsClass() {
        driver.get("https://www.saucedemo.com/");

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.tagName("div"));
        WebElement element2 = driver.findElement(By.tagName("input"));

        // Hover
        actions.moveToElement(element).build().perform();

        // Drag & Drop
        actions.dragAndDrop(element, element2).build().perform();

        // Click, Pause, Release
        actions.clickAndHold(element).pause(100).release().build().perform();
    }
}
