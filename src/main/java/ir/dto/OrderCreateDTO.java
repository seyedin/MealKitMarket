package ir.dto;

import ir.entity.enums.OrderStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record OrderCreateDTO(

        @NotNull
        @Future(message = "Delivery date must be in the future")
        LocalDateTime deliveryDate,

        @NotBlank(message = "Status is mandatory")
        @Enumerated(EnumType.STRING)
        OrderStatus status,

        @NotNull
        @PositiveOrZero(message = "Total price cannot be negative")
        Double totalPrice
) {
}
