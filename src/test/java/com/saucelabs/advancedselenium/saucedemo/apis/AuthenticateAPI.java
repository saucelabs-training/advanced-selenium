package com.saucelabs.advancedselenium.saucedemo.apis;

import com.saucelabs.advancedselenium.saucedemo.Browser;
import com.saucelabs.advancedselenium.saucedemo.data.User;

public class AuthenticateAPI extends BaseAPI {
    public AuthenticateAPI(Browser browser) {
        super(browser);
    }

    public void authenticate() {
        addCookie("session-username", User.valid().getUsername());
    }

    public Boolean isAuthenticated() {
        return getCookie("session-username") != null;
    }
}
