package test.data;

import com.github.javafaker.Faker;

public class Person {

    public String firstName;
    public String lastName;
    public String zipCode;
    private static Faker faker = new Faker();

    // Implement "Default Data"
    public Person() {
    }

    // Implement "Contextual Data"
    public static Person beverlyHills() {
        return new Person();
    }
}
