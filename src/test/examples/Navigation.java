package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import test.base.Base;

public class Navigation extends Base {

    @Test
    @DisplayName("Example Code for Navigation")
    public void browserNavigation() {
        driver.get("https://www.saucedemo.com/");

        driver.navigate().to("http://google.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
    }
}
