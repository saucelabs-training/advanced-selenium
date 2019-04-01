package test.pages;

import test.data.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {

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
        getElement(this.username).sendKeys(user.getUsername());
        getElement(this.password).sendKeys(user.getPassword());
        getElement(this.submit).click();
    }


    // These are here for previous exercises
    public WebElement getUsername() {
        return driver.findElement(By.id("user-name"));
    }
    public WebElement getPassword() {
        return driver.findElement(By.id("password"));
    }
    public WebElement getSubmit() {
        return driver.findElement(By.className("btn_action"));
    }
}
