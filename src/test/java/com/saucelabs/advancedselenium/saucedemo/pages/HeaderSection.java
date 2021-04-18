package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class HeaderSection extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");
    private final By shoppingCartBadgeImage = By.className("shopping_cart_badge");

    public HeaderSection(RemoteWebDriver driver) {
        super(driver);
    }

    public int cartItems() {
        if (getElements("shoppingCartBadgeImage").isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(getElement("shoppingCartBadgeImage").getText());
        }
    }

    public void logout() {
        click("menuButton");
        click("logoutLink");
    }
}
