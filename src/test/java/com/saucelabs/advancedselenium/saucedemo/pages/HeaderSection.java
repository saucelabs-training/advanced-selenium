package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.elements.Element;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class HeaderSection extends BasePage {
    private final Element menuButton = getElement(By.id("react-burger-menu-btn"), "Menu Button");
    private final Element logoutLink = getElement(By.id("logout_sidebar_link"), "Logout Link");
    private final Element shoppingCartBadgeImage = getElement(By.className("shopping_cart_badge"), "Shopping Cart Badge Image");

    public HeaderSection(RemoteWebDriver driver) {
        super(driver);
    }

    public int cartItems() {
        if (shoppingCartBadgeImage.isElementPresent()) {
            return Integer.parseInt(shoppingCartBadgeImage.getText());
        } else {
            return 0;
        }
    }

    public void logout() {
        menuButton.click();
        logoutLink.click();
    }
}
