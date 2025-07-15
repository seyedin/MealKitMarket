package ir.service.cart;

import ir.dto.cart.CartCreateDTO;
import ir.dto.cart.CartResponseDTO;
import ir.dto.cartMealKit.CartMealKitCreateDTO;
import ir.entity.Customer;
import ir.entity.User;

public interface CartService {
    CartResponseDTO createCartWithMealKits(CartCreateDTO createDTO, User user);

    CartResponseDTO createCart(Customer customer);

    CartResponseDTO addMealKitToCart(User user, CartMealKitCreateDTO createDTO);

    void deleteMealKitFromCart(User user, Long mealKitId);

    CartResponseDTO getCart(Long cartId);

    CartResponseDTO getCartByCustomer(User user);
}
