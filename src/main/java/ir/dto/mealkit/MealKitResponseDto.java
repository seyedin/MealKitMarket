package ir.dto.mealkit;

import java.util.List;

public record MealKitResponseDto(
        Long response,
        String name,
        String description,
        Double price,
        Integer calories,
        Integer prepTime,
        String imageUrl,
        List<String> categories
) {
}
