package ir.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryCreateDTO(
        @NotBlank(message = "Name is mandatory")
        String name,

        String description
) {
}
