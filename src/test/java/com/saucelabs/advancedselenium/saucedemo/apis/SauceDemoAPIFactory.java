package com.saucelabs.advancedselenium.saucedemo.apis;

import com.saucelabs.advancedselenium.saucedemo.Browser;

public class SauceDemoAPIFactory {
    private final Browser browser;
    private AuthenticateAPI authenticateAPI;
    private InventoryAPI inventoryAPI;
    private CartAPI cartAPI;

    public SauceDemoAPIFactory(Browser browser) {
        this.browser = browser;
    }

    public AuthenticateAPI getAuthenticateAPI() {
        if (this.authenticateAPI == null) {
            this.authenticateAPI = new AuthenticateAPI(browser);
        }
        return this.authenticateAPI;
    }

    public InventoryAPI getInventoryAPI() {
        if (this.inventoryAPI == null) {
            this.inventoryAPI = new InventoryAPI(browser);
        }
        return this.inventoryAPI;
    }

    public CartAPI getCartAPI() {
        if (this.cartAPI == null) {
            this.cartAPI = new CartAPI(browser);
        }
        return this.cartAPI;
    }
}
