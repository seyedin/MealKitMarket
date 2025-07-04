package ir.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ir.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "categories")
@Setter
@Getter
public class Category extends BaseEntity {

    @NotBlank(message = "Name is mandatory")
    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    /**
     * The {@code @JsonBackReference} annotation is used to prevent
     * infinite recursion when converting to JSON by skipping this side
     * during serialization.
     */
    @ManyToMany(mappedBy = "categories")
    @JsonBackReference
    private List<MealKit> mealKits = new ArrayList<>();
}