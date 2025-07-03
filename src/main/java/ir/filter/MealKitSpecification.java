package ir.filter;

import ir.dto.filter.MealKitRequestFilterDto;
import ir.entity.MealKit;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MealKitSpecification {

    public static Specification<MealKit> filterMealKit(MealKitRequestFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Join with categories (correct field name)
            var categoryJoin = root.join("categories", jakarta.persistence.criteria.JoinType.INNER);

            // Filter by mealKit name (partial match)
            if (filterDto.name() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%" + filterDto.name().toLowerCase() + "%"));
            }

            // Filter by category name (partial match)
            if (filterDto.categoryName() != null) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(categoryJoin.get("name")),
                        "%" + filterDto.categoryName().toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}