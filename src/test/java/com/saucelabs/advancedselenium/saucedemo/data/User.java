package com.saucelabs.advancedselenium.saucedemo.data;

public class User {
    private String username;
    private String password;

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
