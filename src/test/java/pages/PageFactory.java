package test.java.pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    public static AddressBookBasePage addressBookPage() {
        WebDriver driver = BasePage.getDriver();
        // This implementation requires knowing implementation
        // Prefer conditional based on type or configuration
        if (driver.manage().window().getSize().width < 992) {
            return new AddressBookMobilePage();
        } else {
            return new AddressBookBasePage();
        }
    }
}
