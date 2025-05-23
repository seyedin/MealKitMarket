package ir.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressCreateDTO(
        @NotBlank(message = "Street is mandatory")
        String street,

        @NotBlank(message = "City is mandatory")
        String city,

        @NotBlank(message = "Postal code is mandatory")
        String postalCode,

        @NotBlank(message = "Country is mandatory")
        String country
) {
}
