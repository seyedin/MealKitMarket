package ir.dto.cart;

import ir.dto.cartMealKit.CartMealKitCreateDTO;

import java.util.List;

public record CartCreateDTO(
        List<CartMealKitCreateDTO> cartMealKits
) {
}
