package test.examples;

import test.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JavaScriptExecutor extends Base {

    @Test
    @DisplayName("Example Code for Executing JavaScript")
    public void jsExecute() throws InterruptedException {
        driver.get("https://www.saucedemo.com/inventory.html");

        WebElement element = driver.findElement(By.id("shopping_cart_container"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        js.executeScript("window.scrollBy(0,500)");

        Thread.sleep(5000);
    }
}
