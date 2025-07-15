package ir.service.cart;

import ir.dto.cart.CartCreateDTO;
import ir.dto.cart.CartResponseDTO;
import ir.dto.cartMealKit.CartMealKitCreateDTO;
import ir.entity.*;
import ir.exception.BusinessNotFoundException;
import ir.mapper.CartMapper;
import ir.repository.CartMealKitRepository;
import ir.repository.CartRepository;
import ir.service.customer.CustomerService;
import ir.service.mealKit.MealKitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MealKitService mealKitService;
    private final CustomerService customerService;
    private final CartMealKitRepository cartMealKitRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartResponseDTO createCartWithMealKits(CartCreateDTO createDTO, User user) {
        Customer customer = customerService.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessNotFoundException("Customer not found!"));
        List<Long> mealKitIds = createDTO.cartMealKits().stream()
                .map(CartMealKitCreateDTO::mealKitId)
                .toList();

        List<MealKit> mealKits = mealKitService.findAllById(mealKitIds);
        return cartMapper.toResponseDTO(
                cartRepository.save(
                        cartMapper.toEntity(createDTO, customer, mealKits)
                ));
    }

    @Override
    @Transactional
    public CartResponseDTO createCart(Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartMealKits(new ArrayList<>());
        return cartMapper.toResponseDTO(
                cartRepository.save(cart));
    }


    @Override
    @Transactional
    public CartResponseDTO addMealKitToCart(User user, CartMealKitCreateDTO createDTO) {
        Customer customer = customerService.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessNotFoundException("Customer not found!"));
        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new BusinessNotFoundException("Cart not found!"));
        MealKit mealKit = mealKitService.findById(createDTO.mealKitId())
                .orElseThrow(() -> new BusinessNotFoundException("MealKit not found!"));
        CartMealKit cartMealKit = new CartMealKit(
                cart, mealKit, createDTO.quantity(), createDTO.portionSize());
        cartMealKitRepository.save(cartMealKit);
        if (cart.getCartMealKits() == null)
            cart.setCartMealKits(new ArrayList<>());
        cart.getCartMealKits().add(cartMealKit);
        return cartMapper.toResponseDTO(
                cartRepository.save(cart));
    }

    @Override
    @Transactional
    public void deleteMealKitFromCart(User user, Long mealKitId) {
        Customer customer = customerService.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessNotFoundException("Customer not found!"));
        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new BusinessNotFoundException("Cart not found!"));
        CartMealKit cartMealKit = cart.getCartMealKits()
                .stream().filter(c -> c.getMealKit().getId().equals(mealKitId))
                .findFirst()
                .orElseThrow(() -> new BusinessNotFoundException("MealKit not found!"));
        cart.getCartMealKits().remove(cartMealKit);
        cartMealKitRepository.delete(cartMealKit);
        cartRepository.save(cart);
    }


    @Override
    @Transactional
    public CartResponseDTO getCart(Long cartId) {
        return cartMapper.toResponseDTO(
                cartRepository.findById(cartId)
                        .orElseThrow(() -> new BusinessNotFoundException("Cart not found!"))
        );
    }

    @Override
    @Transactional
    public CartResponseDTO getCartByCustomer(User user) {
        Customer customer = customerService.findByUserId(user.getId())
                .orElseThrow(()-> new BusinessNotFoundException("Customer not found!"));
        return cartMapper.toResponseDTO(
                cartRepository.findByCustomerId(customer.getId())
                        .orElseThrow(() -> new BusinessNotFoundException("Cart not found!"))
        );
    }
}
