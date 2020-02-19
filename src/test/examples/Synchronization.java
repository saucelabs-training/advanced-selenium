package test.examples;

import test.base.Eager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class Synchronization extends Eager {

    @Test
    @DisplayName("Show Synchronization Issue")
    void synchronization() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name"));
    }
}
