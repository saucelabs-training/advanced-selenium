package test.java.pages;

import org.openqa.selenium.By;

public class AddressBookMobilePage extends AddressBookBasePage {
    protected final By HAMBURGER = By.cssSelector("button.navbar-toggler");

    @Override
    public void navigateToSignInPage() {
        locateDisplayedElement("Hamburger", HAMBURGER).click();
        locateDisplayedElement("Sign In Link", SIGN_IN_LINK).click();
    }
}
