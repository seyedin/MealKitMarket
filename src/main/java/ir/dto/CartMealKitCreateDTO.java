package ir.dto;

import ir.entity.Cart;
import ir.entity.MealKit;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartMealKitCreateDTO(
        @NotNull
        Cart cart,

        @NotNull
        MealKit mealKit,

        @NotNull
        @Positive(message = "Quantity must be positive")
        Integer quantity,

        @NotNull
        @Min(value = 1, message = "Portion size must be at least 1")
        @Max(value = 6, message = "Portion size cannot exceed 6")
        Integer portionSize
) {
}
