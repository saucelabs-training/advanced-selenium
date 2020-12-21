package test.java.browser;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

public class JSExecutorTest extends SauceTestBase {

    @Test
    public void jsExecute() {
        driver.get("http://a.testaddressbook.com");

        WebElement element = driver.findElement(By.id("sign-in"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        js.executeScript("window.scrollBy(0,50)");
    }

}

