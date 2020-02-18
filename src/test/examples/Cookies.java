package test.examples;

import test.base.Base;
import org.openqa.selenium.Cookie;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Cookies extends Base {

    @Test
    @DisplayName("Example Code for Cookies")
    public void cIsForCookie() {
        driver.get("https://www.saucedemo.com/");

        // Create
        Cookie cookie = new Cookie.Builder("name", "value")
                .domain("www.saucedemo.com")
                .expiresOn(new Date(System.currentTimeMillis() + 500000000))
                .isHttpOnly(true)
                .isSecure(true)
                .path("/mypath")
                .build();

        // Add
        driver.manage().addCookie(cookie);

        // Read
        driver.get("https://https://www.saucedemo.com/mypath");
        driver.manage().getCookieNamed("name");

        // Get All
        driver.manage().getCookies();

        // Delete
        driver.manage().deleteCookie(cookie);
    }
}
