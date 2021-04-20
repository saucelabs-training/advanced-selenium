package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InformationPage {
    private RemoteWebDriver driver;

    public InformationPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPage submitForm(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        driver.findElement(By.id("continue")).click();
        return new CheckoutPage(driver);
    }

    public WebElement getFirstNameElement() {
        return driver.findElement(By.id("first-name"));
    }

    public WebElement getLastNameElement() {
        return driver.findElement(By.id("last-name"));
    }

    public WebElement getPostalCodeElement() {
        return driver.findElement(By.id("postal-code"));
    }

    public WebElement getContinueButton() {
        return driver.findElement(By.id("continue"));
    }
}
