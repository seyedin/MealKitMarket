package ir.mapper;

import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.entity.Category;
import ir.entity.MealKit;
import org.springframework.stereotype.Component;

@Component
public class MealKitMapper {

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
}
