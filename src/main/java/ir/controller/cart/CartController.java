package ir.controller.cart;

import ir.dto.cart.CartCreateDTO;
import ir.dto.cart.CartResponseDTO;
import ir.dto.cartMealKit.CartMealKitCreateDTO;
import ir.entity.User;
import ir.service.cart.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
@RequiredArgsConstructor
//@Validated
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<CartResponseDTO> createCartWithMealKits (
            @Valid @RequestBody CartCreateDTO createDTO,
            @AuthenticationPrincipal User user
            ){
        return ResponseEntity.ok(
                cartService.createCartWithMealKits(createDTO, user)
        );
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addMealKitToCart(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid CartMealKitCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                cartService.addMealKitToCart(user, createDTO)
        );
    }

    @DeleteMapping("/meal-kit/{mealKitId}")
    public ResponseEntity<Void> deleteMealKitFromCart (
            @AuthenticationPrincipal User user,
            @PathVariable Long mealKitId
            ){
        cartService.deleteMealKitFromCart(user, mealKitId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartResponseDTO> getCart (
            @PathVariable Long cartId
    ){
        return ResponseEntity.ok(
                cartService.getCart(cartId)
        );
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCartByCustomer(
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
                cartService.getCartByCustomer(user)
        );
    }
}
