package ir.mapper;

import ir.dto.category.CategoryCreateDTO;
import ir.dto.category.CategoryResponseDto;
import ir.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toEntity(CategoryCreateDTO dto) {
        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());
        return category;

    }
    public CategoryResponseDto toResponseDTO(Category category) {
        return new CategoryResponseDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}
