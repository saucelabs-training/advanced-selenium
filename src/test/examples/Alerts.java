package test.examples;

import test.base.Base;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Alerts extends Base {

    @Test
    @DisplayName("Example Code for Handling Alerts")
    public void alertHandling() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('Hello World');");

        Alert alert = driver.switchTo().alert();

        // "Hello World"
        alert.getText();

        // Close Alert
        alert.dismiss();
    }
}
