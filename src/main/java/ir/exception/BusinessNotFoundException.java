package ir.exception;

public class BusinessNotFoundException extends RuntimeException {
    public BusinessNotFoundException(String message) {
        super(message);
    }
}
