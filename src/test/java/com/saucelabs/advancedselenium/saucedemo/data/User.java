package test.java.com.saucelabs.advancedselenium.saucedemo.data;

import com.github.javafaker.Faker;

public class User {
    private String user;
    private String password;

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

    public User() {
        Faker faker = new Faker();
        this.user = faker.name().username();
        this.password = faker.internet().password();
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
