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
    @Min(value = 1, message = "Servings must be at least 1")
    @Max(value = 6, message = "Servings cannot exceed 6")
    private Integer servings;

    @NotNull
    @Positive(message = "Preparation time must be positive")
    private Integer prepTime;

    private String imageUrl;

    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MealKitIngredient> mealKitIngredients;

    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "mealKit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

}

