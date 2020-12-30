package test.java.data;

import com.github.javafaker.Faker;

public class Person extends DataModel {
    private final Faker faker = new Faker();

    // Add Fields with random default data
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String postalCode = faker.address().zipCode();

    // Implement Getters & Setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public static Person beverlyHills() {
        // Update this so it uses a person with zip code "90210"
        Person person = new Person();
        person.postalCode = "90210";
        return person;
    }
}
