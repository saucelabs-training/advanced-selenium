package com.saucelabs.advancedselenium.saucedemo.pages;

public enum Product {
    BACKPACK("sauce-labs-backpack"),
    BIKE_LIGHT("sauce-labs-bike-light"),
    BOLT_SHIRT("sauce-labs-bolt-t-shirt"),
    FLEECE_JACKET("sauce-labs-fleece-jacket"),
    ONESIE("sauce-labs-onesie"),
    TATT_SHIRT("test.allthethings()-t-shirt-(red)");

    private final String id;

    Product(String id) { this.id = id; }

    public String getId() {
        return id;
    }
}
