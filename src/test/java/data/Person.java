package test.java.data;

import com.github.javafaker.Faker;

public class Person {
    private final Faker faker = new Faker();

    // Add Fields
    private String firstName;
    private String lastName;
    private String postalCode;

    // Add Constructor with random data
    public Person() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.postalCode = faker.address().zipCode();
    }

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
