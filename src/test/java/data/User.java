package test.java.data;

import com.github.javafaker.Faker;

public class User extends DataModel {
    private String username = faker.name().username();
    private String password = faker.internet().password();

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
