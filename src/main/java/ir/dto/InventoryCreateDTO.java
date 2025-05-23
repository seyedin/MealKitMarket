package ir.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record InventoryCreateDTO(
        @NotNull
        @PositiveOrZero(message = "Quantity cannot be negative")
        Double quantity
) {
}
