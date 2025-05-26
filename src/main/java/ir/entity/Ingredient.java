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
@Table(name = "ingredients")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;

    @NotBlank(message = "Unit is mandatory")
    @Column(nullable = false)
    private String unit;

    private String description;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MealKitIngredient> mealKitIngredients;

    @OneToOne(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private Inventory inventory;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    private List<ExcludedIngredient> excludedIngredients;
}
