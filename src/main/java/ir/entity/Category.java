package ir.entity;

import ir.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
public class Category extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "categories")
    private List<MealKit> mealKits;
}
