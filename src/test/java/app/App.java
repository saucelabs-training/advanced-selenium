package test.java.app;

import test.java.data.address.User;
import test.java.pages.address.NavBar;

public class App {
    public boolean isAuthenticated(User user) {
        return new NavBar().isLoggedIn(user);
    }

    public boolean isAuthenticated() {
        return new NavBar().isLoggedIn();
    }
}
