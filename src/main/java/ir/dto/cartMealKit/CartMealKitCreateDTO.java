package ir.dto.cartMealKit;

public record CartMealKitCreateDTO(
        Long mealKitId,
        Integer quantity,
        Integer portionSize
) {
}
