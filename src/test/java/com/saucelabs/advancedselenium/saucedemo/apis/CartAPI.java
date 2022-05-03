package com.saucelabs.advancedselenium.saucedemo.apis;

import com.saucelabs.advancedselenium.saucedemo.Browser;

import java.util.Arrays;

public class CartAPI extends BaseAPI {
    public CartAPI(Browser browser) {
        super(browser);
    }

    public Integer getItemCount() {
        String item = getLocalStorage().getItem("cart-contents");
        if (item == null || "[]".equals(item)) {
            return 0;
        }
        return Arrays.asList(item.substring(1, item.length() - 1).split(",")).size();
    }
}
