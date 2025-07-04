package ir.dto.filter;

import java.util.List;

public record MealKitResponseFilterDTO(
//        Long id,
        String name,
        String description,
        Double price,
        Integer calories,
        Integer prepTime,
        String imageUrl,
        List<CategoryDTO> categories
) {
    public record CategoryDTO(
//            Long id,
            String name,
            String description
    ) {
    }
}