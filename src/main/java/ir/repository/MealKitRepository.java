package ir.repository;

import ir.entity.MealKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MealKitRepository extends
        JpaRepository<MealKit, Long>,
        JpaSpecificationExecutor<MealKit> {

    Optional<MealKit> findByName(String name);
}
