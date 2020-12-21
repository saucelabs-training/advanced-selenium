package test.java.browser;

import org.junit.Test;
import test.java.SauceTestBase;

public class BrowserInteractionsTest extends SauceTestBase {

    @Test
    public void browserInformation() {
        driver.get("http://a.testaddressbook.com");

        // "Address Book"
        driver.getTitle();

        // "http://a.testaddressbook.com/"
        driver.getCurrentUrl();

        // "<html> ... </html>"
        driver.getPageSource();
    }

    @Test
    public void browserNavigation() {
        driver.get("http://a.testaddressbook.com");

        driver.navigate().to("http://google.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
    }
}
