package ir.service.mealKit;

import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import ir.entity.Category;
import ir.entity.MealKit;
import ir.exception.BusinessDuplicateException;
import ir.exception.BusinessNotFoundException;
import ir.mapper.MealKitMapper;
import ir.repository.MealKitRepository;
import ir.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealKitServiceImpl implements MealKitService {
    private final MealKitRepository mealKitRepository;
    private final MealKitMapper mealKitMapper;

    private final CategoryService categoryService;

    @Transactional
    @Override
    public MealKitResponseDto createMealKit(MealKitCreateDTO createDto) {
        if(mealKitRepository.findByName(createDto.name()).isPresent())
            throw new BusinessDuplicateException("name", createDto.name());

        List<Category> categories = categoryService.findAllByIds(createDto.categoryIds());

        MealKit mealKit = mealKitMapper.toEntity(createDto);
        mealKit.setCategories(categories);

        return mealKitMapper.toDto(mealKitRepository.save(mealKit));
    }

    @Transactional
    @Override
    public MealKitResponseDto updateCategories(Long mealKitId, MealKitUpdateCategoriesDTO dto) {

        MealKit mealKit = mealKitRepository.findById(mealKitId)
                .orElseThrow(() -> new BusinessNotFoundException("MealKit with ID " + mealKitId + " not found"));

        List<Category> categories = categoryService.findAllByIds(dto.categoryIds());


        mealKit.setCategories(categories);

        return mealKitMapper.toDto(mealKitRepository.save(mealKit));
    }
}
