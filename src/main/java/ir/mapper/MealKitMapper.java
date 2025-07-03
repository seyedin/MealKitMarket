package ir.mapper;

import ir.dto.filter.MealKitResponseFilterDTO;
import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.entity.Category;
import ir.entity.MealKit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MealKitMapper {

    /**
     * Converts a MealKitCreateDTO into a MealKit entity.
     * This method maps all provided input fields from the DTO
     * to a new MealKit instance, without setting any relationships.
     * <p>
     * Typically used when creating new MealKit records from user input.
     *
     * @param dto the data transfer object containing creation data
     * @return a new MealKit entity with populated fields
     */
    public MealKit toEntity(MealKitCreateDTO dto){
        MealKit mealKit = new MealKit();
        mealKit.setName(dto.name());
        mealKit.setDescription(dto.description());
        mealKit.setPrice(dto.price());
        mealKit.setCalories(dto.calories());
        mealKit.setPrepTime(dto.prepTime());
        mealKit.setImageUrl(dto.imageUrl());
        return mealKit;
    }

    /**
     * This method provides a simplified view of the meal kit,
     * including basic information and category names only.
     *
     * @param mealKit the MealKit entity to convert
     * @return a MealKitResponseDto containing summary information
     */
    public MealKitResponseDto toDto(MealKit mealKit){
        return new MealKitResponseDto(
                mealKit.getId(),
                mealKit.getName(),
                mealKit.getDescription(),
                mealKit.getPrice(),
                mealKit.getCalories(),
                mealKit.getPrepTime(),
                mealKit.getImageUrl(),
                mealKit.getCategories().stream().map(Category::getName).toList()
        );
    }

    /**
     * This method is used for filtered search responses and includes
     * detailed meal kit information along with full category details.
     *
     * @param mealKit the MealKit entity to convert
     * @return a MealKitResponseFilterDTO containing full information
     * suitable for paginated filter results
     */
    public MealKitResponseFilterDTO toFilterDto(MealKit mealKit) {
        List<MealKitResponseFilterDTO.CategoryDTO> categoryDTOs = mealKit.getCategories().stream()
                .map(cat -> new MealKitResponseFilterDTO.CategoryDTO(cat.getName(), cat.getDescription()))
                .toList();

        return new MealKitResponseFilterDTO(
//                mealKit.getId(),
                mealKit.getName(),
                mealKit.getDescription(),
                mealKit.getPrice(),
                mealKit.getCalories(),
                mealKit.getPrepTime(),
                mealKit.getImageUrl(),
                categoryDTOs
        );
    }
}
