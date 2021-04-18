package test.java.com.saucelabs.advancedselenium.saucedemo.data;

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
