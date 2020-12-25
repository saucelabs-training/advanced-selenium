package test.java.pages;

public class AddressBookSubclassPage extends AddressBookPage {
    public static AddressBookSubclassPage visit() {
        AddressBookSubclassPage addressBookPage = new AddressBookSubclassPage();
        addressBookPage.navigateTo();
        return addressBookPage;
    }

    @Override
    public void navigateToSignInPage() {
        locateDisplayedElement("Hamburger", HAMBURGER).click();
        locateDisplayedElement("Sign In Link", SIGN_IN_LINK).click();
    }
}
