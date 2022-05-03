package com.saucelabs.advancedselenium.saucedemo.data;

public class Person extends BaseData {
    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String postalCode = faker.address().zipCode();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
