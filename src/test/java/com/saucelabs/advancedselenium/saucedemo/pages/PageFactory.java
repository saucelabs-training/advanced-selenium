package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.remote.RemoteWebDriver;

public class PageFactory {
    public static HomePage home(RemoteWebDriver driver) {
        return new HomePage(driver);
    }

    public static InventoryPage inventory(RemoteWebDriver driver) {
        return new InventoryPage(driver);
    }

    public static HeaderSection header(RemoteWebDriver driver) {
        return new HeaderSection(driver);
    }

    public static CartPage cart(RemoteWebDriver driver) {
        return new CartPage(driver);
    }

    public static ProductPage product(RemoteWebDriver driver) {
        return new ProductPage(driver);
    }

    public static CheckoutPage checkout(RemoteWebDriver driver) {
        return new CheckoutPage(driver);
    }

    public static InformationPage information(RemoteWebDriver driver) {
        return new InformationPage(driver);
    }

    public static FinishPage finish(RemoteWebDriver driver) {
        return new FinishPage(driver);
    }
}
