package com.saucelabs.advancedselenium.saucedemo.apis;

import com.saucelabs.advancedselenium.saucedemo.Browser;

import java.util.Random;

public class InventoryAPI extends BaseAPI{
    public InventoryAPI(Browser browser) {
        super(browser);
    }

    public void addItemSuccessfully() {
        int productID = new Random().nextInt(5);
        getLocalStorage().setItem("cart-contents", "[" + String.valueOf(productID) + "]");
    }
}
