package ir.dto.customer;

import ir.dto.user.UserResponseDTO;

public record CustomerResponseDTO(
        Long id,
        UserResponseDTO user
) {
}
