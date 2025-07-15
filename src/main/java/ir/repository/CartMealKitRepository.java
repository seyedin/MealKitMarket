package ir.repository;

import ir.entity.CartMealKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMealKitRepository extends JpaRepository<CartMealKit, Long> {
}
