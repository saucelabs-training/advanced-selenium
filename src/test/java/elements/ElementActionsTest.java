package test.java.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class ElementActionsTest extends SauceTestBase {

    @Test
    public void signIn() {
        driver.get("http://a.testaddressbook.com");

        // Take actions on element
        driver.findElement(By.id("sign-in")).click();

        WebElement email = driver.findElement(By.id("session_email"));

        email.clear();
        email.sendKeys("user@example.com");

        driver.findElement(By.id("session_password")).sendKeys("password");

        driver.findElement(By.tagName("form")).submit();
    }

}