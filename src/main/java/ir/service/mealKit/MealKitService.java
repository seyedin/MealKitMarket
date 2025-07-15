package ir.service.mealKit;

import ir.dto.filter.MealKitRequestFilterDto;
import ir.dto.filter.MealKitResponseFilterDTO;
import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import ir.entity.MealKit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MealKitService {
    MealKitResponseDto createMealKit(MealKitCreateDTO createDto);

    @Transactional
    MealKitResponseDto updateCategories(Long mealKitId, MealKitUpdateCategoriesDTO dto);

    Page<MealKitResponseFilterDTO> getFilteredMealKits(MealKitRequestFilterDto filterDto, Pageable pageable);

    Optional<MealKit> findById(Long mealKitId);

    List<MealKit> findAllById(List<Long> mealKitIds);
}
