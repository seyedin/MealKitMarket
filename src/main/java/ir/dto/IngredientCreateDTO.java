package ir.dto;

import jakarta.validation.constraints.NotBlank;

public record IngredientCreateDTO(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Unit is mandatory")
        String unit,

        String description
) {
}
