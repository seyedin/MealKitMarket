package ir.controller;

import ir.dto.mealkit.MealKitCreateDTO;
import ir.dto.mealkit.MealKitResponseDto;
import ir.dto.mealkit.MealKitUpdateCategoriesDTO;
import ir.service.mealKit.MealKitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mealkits")
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


}
