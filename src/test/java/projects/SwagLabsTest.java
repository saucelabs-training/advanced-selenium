package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.SauceTestBase;

import java.util.List;

public class SwagLabsTest extends SauceTestBase {

    @Test
    public void purchaseItems() throws Exception {
        driver.get("https://www.saucedemo.com/");

        // Sign  in with Standard User
        WebElement username = driver.findElement(By.id("user-name"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        // Add a Bolt T-Shirt and a Fleece Jacket to the Cart
        WebElement parent1 = partialStringMatch(By.className("inventory_item"), "Sauce Labs Bolt T-Shirt");
        WebElement button1 = parent1.findElement(By.tagName("button"));
        button1.click();

        WebElement parent2 = partialStringMatch(By.className("inventory_item"), "Sauce Labs Fleece Jacket");
        WebElement button2 = parent2.findElement(By.tagName("button"));
        button2.click();

        // Go to Cart & Check out
        WebElement shopping_cart_link = driver.findElement(By.className("shopping_cart_link"));
        shopping_cart_link.click();

        WebElement checkout_button = driver.findElement(By.className("checkout_button"));
        checkout_button.click();

        // Submit Form with your information

        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("Brandon");

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Walsh");

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("90210");

        WebElement cartButton = driver.findElement(By.className("cart_button"));
        cartButton.click();

        // Finish Purchase
        WebElement finishButton = driver.findElement(By.className("cart_button"));
        finishButton.click();

        // Assert that the Pony Express Image is on the Page
        List<WebElement> ponyExpressImages = driver.findElements(By.className("pony_express"));
        Assert.assertTrue(ponyExpressImages.size() > 0);
    }

    private WebElement partialStringMatch(By locator, String string) throws Exception {
        List<WebElement> elements = driver.findElements(locator);
        for (WebElement element : elements){
            if(element.getText().contains(string)) {
                return element;
            }
        }
        throw new Exception("Can not find an element matching " + string + " with the locator " + locator.toString());
    }
}
