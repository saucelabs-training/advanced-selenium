package test.java.com.saucelabs.advancedselenium.resources.exceptions;

public class ElementValidationException extends RuntimeException {
    public ElementValidationException(String message) {
        super(message);
    }

    public ElementValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
