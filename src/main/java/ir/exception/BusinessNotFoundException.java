package ir.exception;

public class BusinessNotFoundException extends RuntimeException {
    public BusinessNotFoundException(String message) {
        super(message);
    }
    public BusinessNotFoundException(String field, String value) {
        super(field + " with value " + value +  " not found!");
    }
}
