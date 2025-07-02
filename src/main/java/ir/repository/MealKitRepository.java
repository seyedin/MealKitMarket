package ir.repository;

import ir.entity.MealKit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealKitRepository extends JpaRepository<MealKit, Long> {

    Optional<MealKit> findByName(String name);
}
