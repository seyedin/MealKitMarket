package ir.dto.auth;

public record LoginRequestDTO(
        String username,
        String password
) {
}
