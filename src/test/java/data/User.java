package test.java.data;

import com.github.javafaker.Faker;

public class User {
    private final Faker faker = new Faker();

    private String username;
    private String password;

    public User() {
        this.username = faker.name().username();
        this.password = faker.internet().password();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static User blankPassword() {
        User user = new User();
        user.password = "";
        return user;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static User valid() {
        User user = new User();
        user.username = "standard_user";
        user.password = "secret_sauce";
        return user;
    }

    public static User random() {
        Faker faker = new Faker();

        User user = new User();
        user.username = faker.name().username();
        user.password = faker.internet().password();
        return user;
    }
}
