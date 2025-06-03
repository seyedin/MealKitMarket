package ir.exception;

public class BusinessDuplicateException extends RuntimeException {
    public BusinessDuplicateException(String field, String value) {
        super(field + " with value " + value +  " already exists!");
    }
}
