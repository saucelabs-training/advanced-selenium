package test.java.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Product {
    private final Swag swag;

    public enum Swag {
        BACKPACK("Sauce Labs Backpack"),
        BOLT_SHIRT("Sauce Labs Bolt T-Shirt"),
        ONESIE("Sauce Labs Onesie"),
        BIKE_LIGHT("Sauce Labs Bike Light"),
        FLEECE("Sauce Labs Fleece Jacket"),
        TATT_SHIRT_RED("Test.allTheThings() T-Shirt (Red)");

        private static final List<Swag> VALUES = new ArrayList<>(Arrays.asList(Swag.values()));
        private final String value;

        Swag(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Product(Swag swag) {
        this.swag = swag;
    }

    public Product() {
        this.swag = random().swag;
    }

    public String getName() {
        return swag.getValue();
    }

    public static Product random() {
        Collections.shuffle(Swag.VALUES);
        Swag option = Swag.VALUES.remove(0);
        return new Product(option);
    }
}
