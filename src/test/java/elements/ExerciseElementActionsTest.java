package test.java.elements;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.SauceTestBase;

public class ExerciseElementActionsTest extends SauceTestBase {

    //
    // Exercise:
    // Use Selenium to submit the sign up form
    //

    @Test
    public void signUpExistingAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // navigate to desired page
        driver.get("http://a.testaddressbook.com/sign_up");

        // Specify Data

        String email = "user@example.com";
        String password = "password";

        // fill out email field using `email` variable
        driver.findElement(By.id("user_email")).sendKeys(email);

        // fill out password field using `password` variable
        driver.findElement(By.id("user_password")).sendKeys(password);

        // click Sign Up button (or Submit Form)
        driver.findElement(By.name("commit")).click();

        // Note that because this user already exists, Sign Up will not be successful
        wait.until(ExpectedConditions.urlMatches("http://a.testaddressbook.com/users"));
    }

}