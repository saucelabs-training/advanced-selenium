package test.exercises;

import org.openqa.selenium.By;
import test.base.Base;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SeleniumTest extends Base {

    @Test
    @DisplayName("Basic Test")
    void basicTest() {
        driver.get("https://www.saucedemo.com/");

        String email = "standard_user";
        String password = "secret_sauce";

        driver.findElement(By.id("user-name")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("btn_action")).click();
    }
}
