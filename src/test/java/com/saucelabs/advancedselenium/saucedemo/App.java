package test.java.com.saucelabs.advancedselenium.saucedemo;

import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;
import test.java.com.saucelabs.advancedselenium.saucedemo.pages.PageFactory;

public class App {
    private final RemoteWebDriver driver;

    public App(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void login() {
        HomePage homePage = PageFactory.home(driver);
        homePage.visit();
        homePage.loginSuccessfully();
    }

    public void addInventory(int repeats) {
        InventoryPage inventory = PageFactory.inventory(driver);
        inventory.visit();

        for (int i=0; i<repeats;i++) {
            inventory.addItemToCart();
        }
    }

    public void addInformation() {
        InformationPage information = PageFactory.information(driver);
        information.visit();
        information.submitInfoSuccessfully();
    }
}
