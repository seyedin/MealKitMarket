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
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotNull
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private Double price;

    @NotNull
    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @NotNull
    @Positive(message = "Preparation time must be positive")
    @Column(nullable = false)
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

