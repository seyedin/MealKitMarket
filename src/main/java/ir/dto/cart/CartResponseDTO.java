package ir.dto.cart;

import ir.dto.cartMealKit.CartMealKitResponseDTO;

import java.util.List;

public record CartResponseDTO(
    Long cartId,
    Long customerId,
    List<CartMealKitResponseDTO> cartMealKits,
    Double totalPrice // Optional but useful
) {}