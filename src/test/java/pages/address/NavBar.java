package test.java.pages.address;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import test.java.element.Element;
import test.java.exceptions.PageValidationException;
import test.java.pages.BasePage;

import java.util.function.Function;

public class NavBar extends BasePage {
    protected Element signOut = browser.element("Sign Out Link", By.cssSelector("[data-test=sign-out]"));
    protected Element currentUser = browser.element("Current User", By.cssSelector("[data-test=current-user]"));

    public void signOutSuccessfully() {
        signOut.click();

        try {
            wait.until((Function<WebDriver, Object>) driver -> !isLoggedIn());
        } catch (TimeoutException e) {
            throw new PageValidationException("Sign Out was not successful after " + DEFAULT_WAIT_TIME + " seconds; "
            + currentUser.getText() + " is still logged in");
        }
    }

    public boolean isLoggedIn() {
        return currentUser.doesExist();
    }
}
