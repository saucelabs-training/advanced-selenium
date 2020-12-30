package test.java.app;

import test.java.data.address.User;
import test.java.pages.address.NavBar;
import test.java.pages.address.SignUpPage;

public class App {
    public boolean isAuthenticated(User user) {
        return new NavBar().isLoggedIn(user);
    }

    public boolean isAuthenticated() {
        return new NavBar().isLoggedIn();
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
