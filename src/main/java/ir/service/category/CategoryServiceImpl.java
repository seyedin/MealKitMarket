package ir.service.category;

import ir.dto.category.CategoryCreateDTO;
import ir.dto.category.CategoryResponseDto;
import ir.entity.Category;
import ir.exception.BusinessDuplicateException;
import ir.exception.BusinessNotFoundException;
import ir.mapper.CategoryMapper;
import ir.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Transactional
    @Override
    public CategoryResponseDto createCategory(CategoryCreateDTO createDto) {
        if (categoryRepository.findByName(createDto.name()).isPresent()) {
            throw new BusinessDuplicateException("name", createDto.name());
        }
        Category category = categoryMapper.toEntity(createDto);

        return categoryMapper.toResponseDTO(categoryRepository.save(category));
    }

    @Override
    public List<Category> findAllByIds(List<Long> ids) {
        List<Category> categories = categoryRepository.findAllById(ids);
        if (categories.size() != ids.size()) {
            List<Long> foundIds = categories.stream().map(Category::getId).toList();
            List<Long> missingIds = ids.stream().filter(id -> !foundIds.contains(id)).toList();
            throw new BusinessNotFoundException("Categories with IDs " + missingIds + " not found");
        }
        return categories;
    }
}
