package test.java.com.saucelabs.advancedselenium.resources.exceptions;

public class PageValidationException extends RuntimeException {
    public PageValidationException(String message) {
        super(message);
    }
}
