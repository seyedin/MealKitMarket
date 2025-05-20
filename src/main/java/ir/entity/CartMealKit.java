package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cart_mealKit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartMealKit extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_kit_id")
    private MealKit mealKit;

    @NotNull
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "Portion size must be at least 1")
    @Max(value = 6, message = "Portion size cannot exceed 6")
    private Integer portionSize;
}
