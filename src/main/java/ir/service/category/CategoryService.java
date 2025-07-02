package ir.service.category;

import ir.dto.category.CategoryCreateDTO;
import ir.dto.category.CategoryResponseDto;
import ir.entity.Category;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryService {
    @Transactional
    CategoryResponseDto createCategory(CategoryCreateDTO dto);

    List<Category> findAllByIds(List<Long> ids);
}
