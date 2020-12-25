package test.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.exceptions.PageValidationException;

public class AddressBookPage extends BasePage {
    protected static final String URL = "http://a.testaddressbook.com/";
    protected static final By SIGN_IN_LINK = By.id("sign-in");
    protected static final By HAMBURGER = By.cssSelector("button.navbar-toggler");

    public static AddressBookPage visit() {
        AddressBookPage addressBookPage = new AddressBookPage();
        addressBookPage.navigateTo();
        return addressBookPage;
    }

    public void navigateToSignInPageDefault() {
        locateElement("Sign In Link", SIGN_IN_LINK).click();
    }

    public void navigateToSignInPageConditional() {
        WebElement hamburger = locateElement("Hamburger", HAMBURGER);
        if (hamburger.isDisplayed()) {
            hamburger.click();
        }
        locateDisplayedElement("Sign In Link", SIGN_IN_LINK).click();
    }

    public void navigateToSignInPage() {
        locateDisplayedElement("Sign In Link", SIGN_IN_LINK).click();
    }

    public void navigateTo() {
        driver.get(URL);
    }

    public void validateSuccessfulNavigation() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(URL)) {
            throw new PageValidationException("Navigation away from AddressBookPage was not successful; Current URL: " + currentUrl);
        }
    }
}
