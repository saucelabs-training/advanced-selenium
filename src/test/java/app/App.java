package test.java.app;

import org.openqa.selenium.WebDriver;
import test.java.data.address.User;
import test.java.pages.address.NavBar;
import test.java.pages.address.SignUpPage;

public class App {
    private final WebDriver driver;

    public App(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAuthenticated(User user) {
        driver.navigate().refresh();
        return new NavBar().isLoggedIn(user);
    }

    public boolean isAuthenticated() {
        return driver.manage().getCookieNamed("remember_token") != null;
    }

    public void authenticateNewUser() {
        SignUpPage signUpPage = SignUpPage.visit();
        signUpPage.signupSuccessfully();
    }

    public User createNewUser() {
        SignUpPage signUpPage = SignUpPage.visit();
        User user = new User();
        signUpPage.signupSuccessfully(user);
        new NavBar().signOutSuccessfully();
        return user;
    }
}
