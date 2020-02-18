package test.examples;

import test.base.Base;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class Windows extends Base {

    @Test
    @DisplayName("Example Code for Switching Between Windows")
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
