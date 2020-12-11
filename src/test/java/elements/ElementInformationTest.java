package test.java.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import test.java.SauceTestBase;

public class ElementInformationTest extends SauceTestBase {

    @Test
    public void signInLink() {
        driver.get("http://a.testaddressbook.com");

        // "Sign In"
        driver.findElement(By.id("sign-in")).getText();

        // "a"
        driver.findElement(By.id("sign-in")).getTagName();

        // "sign-in"
        driver.findElement(By.id("sign-in")).getAttribute("data-test");

        // true
        driver.findElement(By.id("sign-in")).isDisplayed();

        // true
        driver.findElement(By.id("sign-in")).isEnabled();
    }

}