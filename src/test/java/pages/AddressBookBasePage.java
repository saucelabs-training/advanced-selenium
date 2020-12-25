package test.java.pages;

import org.openqa.selenium.By;
import test.java.exceptions.PageValidationException;

public class AddressBookBasePage extends BasePage {
    protected static final String URL = "http://a.testaddressbook.com/";
    protected final By SIGN_IN_LINK = By.id("sign-in");

    public static AddressBookBasePage visit() {
        AddressBookBasePage addressBookPage = new AddressBookBasePage();
        addressBookPage.navigateTo();
        return addressBookPage;
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
