package test.java.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import test.java.SauceTestBase;

public class SelectsTest extends SauceTestBase {

    @Test
    public void selectElement() {
        driver.get("https://the-internet.herokuapp.com/dropdown");

        Select select = new Select(driver.findElement(By.id("dropdown")));

        // Select option by index
        select.selectByIndex(1);
            // "Option 1"
            select.getFirstSelectedOption().getText();
            // "1"
            select.getFirstSelectedOption().getAttribute("value");
            // True
            select.getFirstSelectedOption().isSelected();

        // Select option by value
        select.selectByValue("2");

        // Select option by text
        select.selectByVisibleText("Option 1");
    }

}