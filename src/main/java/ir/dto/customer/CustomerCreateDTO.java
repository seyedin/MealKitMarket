package ir.dto.customer;

import ir.dto.user.UserCreateDTO;

public record CustomerCreateDTO(
        UserCreateDTO userDto
) {
}
