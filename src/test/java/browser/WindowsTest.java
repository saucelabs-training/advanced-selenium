package test.java.browser;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import test.java.SauceTestBase;

import java.util.Set;

public class WindowsTest extends SauceTestBase {

    @Test
    public void windowSwitching() {
        driver.get("https://google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://www.example.com');");

        String origWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        handles.remove(origWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        // "Example Domain"
        driver.switchTo().window(nextWindow);

        driver.close();

        // "Google"
        driver.switchTo().window(origWindow);
    }

}

