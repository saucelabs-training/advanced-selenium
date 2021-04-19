package test.java.com.saucelabs.advancedselenium.saucedemo.data;

import test.java.com.saucelabs.advancedselenium.resources.data.DataModel;

public class User extends DataModel {
    private String user = faker.name().username();
    private String password = faker.internet().password();

    public static User valid() {
        User user = new User();
        user.setUser("standard_user");
        user.setPassword("secret_sauce");
        return user;
    }

    public static User lockedOut() {
        User user = new User();
        user.setUser("locked_out_user");
        user.setPassword("secret_sauce");
        return user;
    }

    public static User invalid() {
        return new User();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
