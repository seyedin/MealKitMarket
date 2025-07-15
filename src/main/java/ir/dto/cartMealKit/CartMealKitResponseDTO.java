package ir.dto.cartMealKit;

import ir.dto.mealkit.MealKitResponseDto;

public record CartMealKitResponseDTO(
    Long id, // cartMealKitId
    MealKitResponseDto mealKit,
    Integer quantity,
    Integer portionSize,
    Double subtotal // unitPrice * quantity * portionSize (if applicable)
) {}