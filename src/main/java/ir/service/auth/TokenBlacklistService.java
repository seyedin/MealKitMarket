package ir.service.auth;

public interface TokenBlacklistService {
    void blacklistToken(String token);

    Boolean isTokenBlacklisted(String token);
}
