package test.java.pages;

import org.openqa.selenium.By;
import test.java.exceptions.PageValidationException;

public class HomePage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.id("login-button");
    private static final By ERROR = By.cssSelector("[data-test=error]");

    public static HomePage visit() {
        HomePage homePage = new HomePage();
        homePage.navigateTo();
        return homePage;
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME).sendKeys(username);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(SUBMIT).click();
    }

    public boolean loginSuccessful() {
        return !driver.getCurrentUrl().equals(URL);
    }

    public boolean badLoginSuccessful() throws InterruptedException {
        Thread.sleep(5000);
        return !doesElementExist(ERROR);
    }

    public void validateSuccessfulLogin() {
        if (!loginSuccessful()) {
            String message = driver.findElement(ERROR).getText();
            throw new PageValidationException("Login was not successful: " + message);
        }
    }
}
