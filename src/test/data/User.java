package test.data;

import com.github.javafaker.Faker;

public class User {
    private String username;
    private String password;
    private static Faker faker;

    public User() {
        this.username = faker.name().username();
        this.password = faker.internet().password();
    }

    public static User random() {
        return new User();
    }

    public static User valid() {
        User user = new User();
        user.username = "standard_user";
        user.password = "secret_sauce";
        return user;
    }

    public static User locked_out() {
        User user = new User();
        user.username = "standard_user";
        user.password = "secret_sauce";
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
