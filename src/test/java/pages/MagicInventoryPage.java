package test.java.pages;

import org.openqa.selenium.By;

public class MagicInventoryPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/inventory.html";

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By SUBMIT = By.id("login-button");

    private static final By SHOPPING_CART = By.className("shopping_cart_link");

    public static MagicInventoryPage visitImperative() {
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();

        driver.get("https://www.saucedemo.com");
        driver.findElement(USERNAME).sendKeys("standard_user");
        driver.findElement(PASSWORD).sendKeys("secret_sauce");
        driver.findElement(SUBMIT).click();

        return magicInventoryPage;
    }

    public static MagicInventoryPage visitReferential() {
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();

        HomePage homePage = new HomePage();
        homePage.navigateTo();
        homePage.login("standard_user", "secret_sauce");

        return magicInventoryPage;
    }

    public static MagicInventoryPage visitDirect() {
        MagicInventoryPage magicInventoryPage = new MagicInventoryPage();

        driver.get(URL);

        return magicInventoryPage;
    }

    public MagicInventoryPage() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(USERNAME).sendKeys("standard_user");
        driver.findElement(PASSWORD).sendKeys("secret_sauce");
        driver.findElement(SUBMIT).click();
    }

    public boolean isOnPage() {
        return URL.equals(driver.getCurrentUrl());
    }

    public void shoppingCart() {
        driver.findElement(SHOPPING_CART).click();
    }
}
