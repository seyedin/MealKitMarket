package ir.exception;

public class UsernameNotFoundException extends BusinessNotFoundException {
    public UsernameNotFoundException(String username) {
        super("Username "+ username +" not found! Please try again.");
    }
}
