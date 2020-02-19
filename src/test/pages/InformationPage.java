package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.data.Person;

public class InformationPage {

    private WebDriver driver;

    // Declare the Elements
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By zipCode = By.id("postal-code");
    private By submit = By.className("btn_primary");


    // Implement Visit Method
    public static InformationPage visit(WebDriver driver) {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        return new InformationPage(driver);
    }

    public InformationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Implement filling out the form
    public void provideInformation(Person person) {
        driver.findElement(this.firstName).sendKeys(person.firstName);
        driver.findElement(this.lastName).sendKeys(person.lastName);
        driver.findElement(this.zipCode).sendKeys(person.zipCode);
        driver.findElement(this.submit).click();
    }
}
