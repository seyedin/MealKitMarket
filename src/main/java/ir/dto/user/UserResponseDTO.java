package ir.dto.user;

public record UserResponseDTO(
        Long id,
        String email,
        String firstName,
        String lastName,
        String username,
        String phoneNumber
) {
}
