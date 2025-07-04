package ir.dto.mealkit;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record MealKitUpdateCategoriesDTO(

        @NotEmpty(message = "At least one category is required")
        List<Long> categoryIds
) {
}
