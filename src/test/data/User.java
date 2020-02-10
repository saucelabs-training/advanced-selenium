package test.data;

public class User {
    private String username;
    private String password;

    public static User valid() {
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
