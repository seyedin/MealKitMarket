package ir.dto.user;

public record UserResponseDTO(
        String email,
        String firstName,
        String lastName,
        String username,
        String phoneNumber
) {
}
