package ir.dto.mealkit;

import jakarta.validation.constraints.*;

import java.util.List;

public record MealKitCreateDTO(
        @NotBlank(message = "Name is mandatory")
        String name,

        @NotBlank(message = "Description is mandatory")
        String description,

        @NotNull
        @Positive(message = "Price must be positive")
        Double price,

        @NotEmpty(message = "At least one category is required")
        List<Long> categoryIds,

        @NotNull
        @Min(value = 1, message = "Portion size must be at least 1")
        @Max(value = 6, message = "Portion size cannot exceed 6")
        Integer portionSize,

        @NotNull
        @PositiveOrZero(message = "Calories cannot be negative")
        Integer calories,

        @NotNull
        @Positive(message = "Preparation time must be positive")
        Integer prepTime,

        String imageUrl
) {
}
