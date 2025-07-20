package ir.repository;

import ir.entity.OrderMealKit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMealKitRepository extends JpaRepository<OrderMealKit, Long> {
}