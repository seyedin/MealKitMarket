package ir.dto;

import ir.dto.user.UserCreateDTO;

public record CustomerCreateDTO(
        UserCreateDTO userDto
) {
}
