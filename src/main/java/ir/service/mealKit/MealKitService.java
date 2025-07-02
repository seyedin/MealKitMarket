package ir.service.mealKit;

import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import org.springframework.transaction.annotation.Transactional;

public interface MealKitService {
    MealKitResponseDto createMealKit(MealKitCreateDTO createDto);

    @Transactional
    MealKitResponseDto updateCategories(Long mealKitId, MealKitUpdateCategoriesDTO dto);
}
