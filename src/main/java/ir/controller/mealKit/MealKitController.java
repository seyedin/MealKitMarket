package ir.controller.mealKit;

import ir.dto.PagedResponse;
import ir.dto.filter.MealKitRequestFilterDto;
import ir.dto.filter.MealKitResponseFilterDTO;
import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import ir.service.mealKit.MealKitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/meal-kits/")
public class MealKitController {

    private final MealKitService mealKitService;

    @PostMapping
    public ResponseEntity<MealKitResponseDto> create(@Valid @RequestBody MealKitCreateDTO createDTO) {
        MealKitResponseDto responseDto = mealKitService.createMealKit(createDTO);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/categories")
    public ResponseEntity<MealKitResponseDto> updateCategories(
            @PathVariable Long id,
            @Valid @RequestBody MealKitUpdateCategoriesDTO dto) {
        MealKitResponseDto responseDto = mealKitService.updateCategories(id, dto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests to filter and retrieve a paginated list of meal kits.
     * <p>
     * This endpoint supports optional filtering by meal kit name and category name,
     * and allows sorting and pagination through query parameters.
     * <p>
     * Example usage:
     * <pre>
     * GET /v1/meal-kits/filter-meal-kits?name=Pizza&categoryName=Italian&pageNumber=0&size=10&sortBy=name&sortDir=asc
     */
    @GetMapping("/filter-meal-kits")
    public ResponseEntity<PagedResponse<MealKitResponseFilterDTO>> getMealKits(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String categoryName,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        MealKitRequestFilterDto requestFilterDto = new MealKitRequestFilterDto(name, categoryName);
        Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageable = PageRequest.of(pageNumber, size, Sort.by(direction, sortBy));

        Page<MealKitResponseFilterDTO> resultPage = mealKitService.getFilteredMealKits(requestFilterDto, pageable);

        PagedResponse<MealKitResponseFilterDTO> response = new PagedResponse<>(resultPage);
        return ResponseEntity.ok(response);
    }
}
