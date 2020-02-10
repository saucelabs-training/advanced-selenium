package test.pages;

import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean onPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
    }
}
