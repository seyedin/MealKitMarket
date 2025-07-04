package ir.controller.category;

import ir.dto.category.CategoryCreateDTO;
import ir.dto.category.CategoryResponseDto;
import ir.service.category.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryCreateDTO dto) {
        CategoryResponseDto responseDto = categoryService.createCategory(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
