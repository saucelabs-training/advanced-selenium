package test.pages;

import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean onPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }
}
