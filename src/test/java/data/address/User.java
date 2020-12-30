package test.java.data.address;

import test.java.data.DataModel;

public class User extends DataModel {
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
