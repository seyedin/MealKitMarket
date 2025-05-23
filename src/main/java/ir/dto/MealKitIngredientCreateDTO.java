package ir.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MealKitIngredientCreateDTO(
        @NotNull
        @Positive(message = "Quantity must be positive")
         Double quantity
) {
}
