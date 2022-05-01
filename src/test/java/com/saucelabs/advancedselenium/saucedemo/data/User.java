package com.saucelabs.advancedselenium.saucedemo.data;

import net.datafaker.Faker;

public class User {
    private final Faker faker = new Faker();
    private String username = faker.name().username();
    private String password = faker.internet().password();

    public static User valid() {
        User user = new User();
        user.setUsername("standard_user");
        user.setPassword("secret_sauce");
        return user;
    }

    public static User lockedOut() {
        User user = new User();
        user.setUsername("locked_out_user");
        user.setPassword("secret_sauce");
        return user;
    }

    public static User invalid() {
        return new User();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
