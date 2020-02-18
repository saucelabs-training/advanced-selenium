package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.base.Base;

public class BrowserInformation extends Base {

    @Test
    @DisplayName("Example Code for Navigation")
    public void browserInformation() {
        driver.get("https://www.saucedemo.com/");

        // "Swag Labs"
        driver.getTitle();

        // "https://www.saucedemo.com/"
        driver.getCurrentUrl();

        // "<html> ... </html>"
        driver.getPageSource();
    }
}
