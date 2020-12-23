package test.java.pages;

import org.openqa.selenium.WebDriver;

public class HomePageMethods {
    private static WebDriver driver;
    private final HomePageElements elements;

    public HomePageMethods(WebDriver driver) {
        HomePageMethods.driver = driver;
        this.elements = new HomePageElements(driver);
    }

    public HomePageMethods navigateTo() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public InventoryPage login(String username, String password) {
        elements.getUsername().sendKeys(username);
        elements.getPassword().sendKeys(password);
        elements.getSubmit().click();
        return new InventoryPage(driver);
    }
}
