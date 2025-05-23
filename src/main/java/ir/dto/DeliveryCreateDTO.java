package ir.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DeliveryCreateDTO(
        @NotNull
        LocalDateTime deliveryDate,

        @NotBlank(message = "Time slot is mandatory")
        String deliveryTimeSlot,

        @NotBlank(message = "Status is mandatory")
        String status
) {
}
