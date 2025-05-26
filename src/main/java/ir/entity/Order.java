package ir.entity;

import ir.entity.base.BaseEntity;
import ir.entity.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    private LocalDateTime orderDate = LocalDateTime.now();

    @NotNull
    @Future(message = "Delivery date must be in the future")
    @Column(nullable = false)
    private LocalDateTime deliveryDate;

    @NotBlank(message = "Status is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @NotNull
    @PositiveOrZero(message = "Total price cannot be negative")
    @Column(nullable = false)
    private Double totalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    // cascade = CascadeType.ALL
    private List<OrderMealKit> orderMealKits;
}
