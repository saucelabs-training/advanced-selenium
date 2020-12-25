package test.java;

import org.junit.Test;
import org.openqa.selenium.By;
import test.java.pages.SauceDemoPage;

public class CustomErrorsTest extends SauceTestBase {

    @Test
    public void findMissingElementDefaultMessage() {
        driver.findElement(By.id("missing-element"));
    }

    @Test
    public void findMissingElementCustomMessage() {
        SauceDemoPage sauceDemoPage = SauceDemoPage.visit();
        sauceDemoPage.locateElementCustomError();
    }
}
