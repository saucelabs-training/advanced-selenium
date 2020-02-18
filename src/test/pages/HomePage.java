package test.pages;

import test.data.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;
    private By username = By.id("user-name");
    private By password = By.id("password");
    private By submit = By.className("btn_action");

    public static HomePage visit(WebDriver driver) {
        driver.get("https://www.saucedemo.com/");
        return new HomePage(driver);
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void signIn(User user) {
        driver.findElement(this.username).sendKeys(user.getUsername());
        driver.findElement(this.password).sendKeys(user.getPassword());
        driver.findElement(this.submit).click();
    }

    // Getters are for the Imperative example; you shouldn't need to have getters on elements
    public WebElement getUsername() {
        return driver.findElement(this.username);
    }

    public WebElement getPassword() {
        return driver.findElement(this.password);
    }

    public WebElement getSubmit() {
        return driver.findElement(this.submit);
    }

}
