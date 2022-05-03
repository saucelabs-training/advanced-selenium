package com.saucelabs.advancedselenium.saucedemo.pages;

public class ElementValidationException extends RuntimeException {
    public ElementValidationException(String message) {
        super(message);
    }

    public ElementValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
