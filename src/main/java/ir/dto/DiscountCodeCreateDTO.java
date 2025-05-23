package ir.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record DiscountCodeCreateDTO(
        @NotBlank(message = "Code is mandatory")
        String code,

        @NotNull
        @Positive(message = "Percentage must be positive")
        @Max(value = 100, message = "Percentage cannot exceed 100")
        Double percentage,

        @NotNull
        LocalDateTime expiryDate
) {
}
