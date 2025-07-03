package ir.service.mealKit;

import ir.dto.filter.MealKitRequestFilterDto;
import ir.dto.filter.MealKitResponseFilterDTO;
import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import ir.entity.Category;
import ir.entity.MealKit;
import ir.exception.BusinessDuplicateException;
import ir.exception.BusinessNotFoundException;
import ir.filter.MealKitSpecification;
import ir.mapper.MealKitMapper;
import ir.repository.MealKitRepository;
import ir.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Retrieves a paginated list of MealKitResponseFilterDTOs based on the provided filter criteria.
     * Applies filtering through MealKitSpecification and maps each MealKit entity to a response DTO.
     * <p>
     * This method is typically used to support filtered search functionality with pagination and sorting.
     *
     * @param filterDto the filtering criteria including optional name and category name
     * @param pageable  the pagination and sorting configuration
     * @return a page of MealKitResponseFilterDTO objects matching the filter
     */
    @Override
    public Page<MealKitResponseFilterDTO> getFilteredMealKits(MealKitRequestFilterDto filterDto, Pageable pageable) {
        Page<MealKit> mealKits = mealKitRepository.findAll(MealKitSpecification.filterMealKit(filterDto), pageable);
        return mealKits.map(mealKitMapper::toFilterDto);
    }
}
