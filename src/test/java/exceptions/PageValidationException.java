package test.java.exceptions;

public class PageValidationException extends RuntimeException {
    public PageValidationException(String message) {
        super(message);
    }
}
