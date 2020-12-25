package test.java.projects;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import test.java.SauceTestBase;
import test.java.exceptions.PageValidationException;
import test.java.pages.AddressBookBasePage;
import test.java.pages.AddressBookPage;
import test.java.pages.AddressBookSubclassPage;
import test.java.pages.PageFactory;

public class DeterminismTest extends SauceTestBase {

    @Test
    public void largeWindow() {
        driver.manage().window().setSize(new Dimension(1200, 900));

        AddressBookPage addressBookPage = AddressBookPage.visit();
        addressBookPage.navigateToSignInPageDefault();

        validateSuccessfulNavigation(addressBookPage);
    }

    @Test
    public void smallWindowBadError() {
        driver.manage().window().setSize(new Dimension(800, 600));

        AddressBookPage addressBookPage = AddressBookPage.visit();
        addressBookPage.navigateToSignInPageDefault();

        validateSuccessfulNavigation(addressBookPage);
    }

    @Test
    public void smallWindowGoodError() {
        driver.manage().window().setSize(new Dimension(800, 600));
        AddressBookPage addressBookPage = AddressBookPage.visit();
        addressBookPage.navigateToSignInPage();

        validateSuccessfulNavigation(addressBookPage);
    }

    // Try running this test with PageLoadStrategy.NONE
    @Test
    public void smallWindowConditional() {
        driver.manage().window().setSize(new Dimension(800, 600));
        AddressBookPage addressBookPage = AddressBookPage.visit();
        addressBookPage.navigateToSignInPageConditional();

        validateSuccessfulNavigation(addressBookPage);
    }

    @Test
    public void smallWindowSubclass() {
        driver.manage().window().setSize(new Dimension(800, 600));

        AddressBookSubclassPage addressBookPage = AddressBookSubclassPage.visit();
        addressBookPage.navigateToSignInPage();

        validateSuccessfulNavigation(addressBookPage);
    }

    @Test
    public void largeWindowFactory() {
        driver.manage().window().setSize(new Dimension(1200, 900));

        AddressBookBasePage addressBookPage = PageFactory.addressBookPage();
        addressBookPage.navigateTo();
        addressBookPage.navigateToSignInPage();

        validateSuccessfulNavigation(addressBookPage);
    }

    @Test
    public void smallWindowFactory() {
        driver.manage().window().setSize(new Dimension(800, 600));

        AddressBookBasePage addressBookPage = PageFactory.addressBookPage();
        addressBookPage.navigateTo();
        addressBookPage.navigateToSignInPage();

        validateSuccessfulNavigation(addressBookPage);
    }


    // Validation Methods

    public void validateSuccessfulNavigation(AddressBookBasePage addressBookPage) {
        try {
            addressBookPage.validateSuccessfulNavigation();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }

    public void validateSuccessfulNavigation(AddressBookPage addressBookPage) {
        try {
            addressBookPage.validateSuccessfulNavigation();
        } catch (PageValidationException e) {
            Assert.fail(e.toString());
        }
    }
}
