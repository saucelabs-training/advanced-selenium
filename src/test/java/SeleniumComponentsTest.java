package test.java;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumComponentsTest {

    @Test
    public void sevenComponents() {

        // 1. Start session (opens browser)
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // 2. Navigate to System Under Test
        driver.get("https://www.saucedemo.com");

        // 3. Synchronize with first element used
        By locator = By.className("btn_action");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

        // 4. Locate element requiring interaction
        WebElement element = driver.findElement(locator);

        // 5. Perform action on element
        element.click();

        // 6. Make Assertion
        Assert.assertEquals("Swag Labs", driver.getTitle());

        // 7. Quit session (closes browser)
        driver.quit();
    }
}

