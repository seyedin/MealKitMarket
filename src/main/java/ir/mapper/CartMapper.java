package ir.mapper;

import ir.dto.cart.CartCreateDTO;
import ir.dto.cart.CartResponseDTO;
import ir.dto.cartMealKit.CartMealKitResponseDTO;
import ir.entity.Cart;
import ir.entity.CartMealKit;
import ir.entity.Customer;
import ir.entity.MealKit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CartMapper {

    private final CartMealKitMapper cartMealKitMapper;

    public Cart toEntity(
            CartCreateDTO createDTO, Customer customer, List<MealKit> mealKits) {
        Cart cart = new Cart();
        List<CartMealKit> cartMealKits = createDTO.cartMealKits()
                .stream().map(cartMealKitCreateDTO -> {
                    MealKit mealKit = mealKits.stream()
                            .filter(m -> m.getId().equals(cartMealKitCreateDTO.mealKitId()))
                            .findFirst()
                            .orElseThrow(() -> new IllegalArgumentException(
                                    "MealKit not found for ID: " + cartMealKitCreateDTO.mealKitId()));
                   return cartMealKitMapper.toEntity(cartMealKitCreateDTO, mealKit, cart);
                }).toList();
        cart.setCustomer(customer);
        cart.setCartMealKits(cartMealKits);
        return cart;
    }

    public CartResponseDTO toResponseDTO(
            Cart cart) {
        List<CartMealKitResponseDTO> cartMealKitResponses = cart.getCartMealKits()
                .stream()
                .map(cartMealKitMapper::toResponseDTO)
                .toList();

        double totalPrice = cartMealKitResponses.stream()
                .mapToDouble(CartMealKitResponseDTO::subtotal)
                .sum();

        return new CartResponseDTO(
                cart.getId(),
                cart.getCustomer().getId(),
                cartMealKitResponses,
                totalPrice
        );
    }
}
