package ir.mapper;

import ir.dto.cartMealKit.CartMealKitCreateDTO;
import ir.dto.cartMealKit.CartMealKitResponseDTO;
import ir.entity.Cart;
import ir.entity.CartMealKit;
import ir.entity.MealKit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMealKitMapper {

    private final MealKitMapper mealKitMapper;

    public CartMealKit toEntity(
            CartMealKitCreateDTO dto, MealKit mealKit, Cart cart) {
        CartMealKit cartMealKit = new CartMealKit();
        cartMealKit.setMealKit(mealKit);
        cartMealKit.setCart(cart);
        cartMealKit.setQuantity(dto.quantity());
        cartMealKit.setPortionSize(dto.portionSize());
        return cartMealKit;
    }

    public CartMealKitResponseDTO toResponseDTO(
            CartMealKit cartMealKit
    ){
        Double unitPrice = cartMealKit.getMealKit().getPrice();
        Double subtotal = unitPrice * cartMealKit.getQuantity() * cartMealKit.getPortionSize();

        return new CartMealKitResponseDTO(
                cartMealKit.getId(),
                mealKitMapper.toDto(cartMealKit.getMealKit()),
                cartMealKit.getQuantity(),
                cartMealKit.getPortionSize(),
                subtotal
        );
    }
}
