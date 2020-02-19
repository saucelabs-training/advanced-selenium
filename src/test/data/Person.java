package test.data;

import com.github.javafaker.Faker;

public class Person {

    public String firstName;
    public String lastName;
    public String zipCode;
    private static Faker faker = new Faker();

    public Person() {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.zipCode = faker.address().zipCode();
    }

    public static Person beverlyHills() {
        Person person = new Person();
        person.zipCode = "90210";
        return person;
    }
}
