package ir.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "meal_kit_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealKitIngredient {

    @EmbeddedId
    private MealKitIngredientId id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_kit_id")
    @MapsId("mealKitId")
    private MealKit mealKit;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    @MapsId("ingredientId")
    private Ingredient ingredient;

    @NotNull
    @Positive(message = "Quantity must be positive")
    private Double quantity;
}
