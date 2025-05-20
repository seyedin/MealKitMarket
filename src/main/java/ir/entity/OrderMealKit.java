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

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_mealKit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderMealKit extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_kit_id")
    private MealKit mealKit;

    @NotNull
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull
    @Min(value = 1, message = "Servings must be at least 1")
    @Max(value = 6, message = "Servings cannot exceed 6")
    private Integer servings;

    @NotNull
    @Positive(message = "Unit price must be positive")
    private Double unitPrice;

    @OneToMany(mappedBy = "orderItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExcludedIngredient> excludedIngredients;
}
