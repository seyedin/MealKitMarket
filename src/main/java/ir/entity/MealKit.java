package ir.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "meal_kits")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

    /**
     * The {@link JsonManagedReference} annotation marks this side of the relationship
     * as the one that should be serialized into JSON.This helps avoid a loop where one object keeps pointing to the other again and again, which could crash the program.
     */
    @ManyToMany
    @JoinTable(
            name = "mealkit_category",
            joinColumns = @JoinColumn(name = "mealkit_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonManagedReference
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<MealKitIngredient> mealKitIngredients;

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<CartMealKit> cartMealKits;

    @OneToMany(mappedBy = "mealKit", fetch = FetchType.LAZY)
    private List<OrderMealKit> orderMealKits;
}

