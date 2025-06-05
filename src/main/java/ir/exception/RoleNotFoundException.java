package ir.exception;

public class RoleNotFoundException extends BusinessNotFoundException {
    public RoleNotFoundException(String role) {
        super("role "+ role +" not found!");
    }
}
