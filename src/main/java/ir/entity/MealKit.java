package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meal_kits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealKit extends BaseEntity {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull
    @Positive(message = "Price must be positive")
    private Double price;

    @NotBlank(message = "Category is mandatory")
    private String category;

    @NotNull
    @Min(value = 1, message = "Portion size must be at least 1")
    @Max(value = 6, message = "Portion size cannot exceed 6")
    private Integer portionSize;

    @NotNull
    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @NotNull
    @Positive(message = "Preparation time must be positive")
    private Integer prepTime;

    private String imageUrl;

    @ManyToMany
    @JoinTable(
            name = "mealkit_category",
            joinColumns = @JoinColumn(name = "mealkit_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<MealKitIngredient> mealKitIngredients;

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<CartMealKit> cartMealKits;

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<OrderMealKit> orderMealKits;
}

