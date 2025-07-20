package ir.service.order;

import ir.dto.order.OrderCreateDTO;
import ir.dto.order.OrderResponseDTO;
import ir.dto.cartMealKit.CartMealKitResponseDTO;
import ir.entity.*;
import ir.entity.enums.OrderStatus;
import ir.exception.BusinessNotFoundException;
import ir.mapper.CartMealKitMapper;
import ir.mapper.OrderMapper;
import ir.repository.*;
import ir.service.customer.CustomerService;
import ir.service.mealKit.MealKitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final MealKitService mealKitService;
    private final InventoryRepository inventoryRepository;
    private final OrderMealKitRepository orderMealKitRepository;
    private final CartMealKitRepository cartMealKitRepository;
    private final OrderMapper orderMapper;
    private final CartMealKitMapper cartMealKitMapper;

    @Transactional
    @Override
    public OrderResponseDTO createOrderFromCart(OrderCreateDTO requestDTO, User user) {
        // پیدا کردن مشتری
        Customer customer = customerService.findByUserId(user.getId())
                .orElseThrow(() -> new BusinessNotFoundException("Customer not found!"));

        // پیدا کردن سبد خرید
        Cart cart = cartRepository.findByCustomerId(customer.getId())
                .orElseThrow(() -> new BusinessNotFoundException("Cart not found!"));

        if (cart.getCartMealKits() == null || cart.getCartMealKits().isEmpty()) {
            throw new BusinessNotFoundException("Cart is empty!");
        }

        // پیدا کردن آدرس
        Address address = customer.getAddresses().stream()
                .filter(a -> a.getId().equals(requestDTO.addressId()))
                .findFirst()
                .orElseThrow(() -> new BusinessNotFoundException("Address not found!"));

        // بررسی موجودی مواد اولیه
        for (CartMealKit cartMealKit : cart.getCartMealKits()) {
            MealKit mealKit = cartMealKit.getMealKit();
            for (MealKitIngredient mki : mealKit.getMealKitIngredients()) {
                Inventory inventory = mki.getIngredient().getInventory();
                double requiredQuantity = mki.getQuantity() * cartMealKit.getQuantity() * cartMealKit.getPortionSize();
                if (inventory.getQuantity() < requiredQuantity) {
                    throw new BusinessNotFoundException("Insufficient inventory for ingredient: " + mki.getIngredient().getName());
                }
            }
        }

        // محاسبه قیمت کل
        double totalPrice = cart.getCartMealKits().stream()
                .mapToDouble(cm -> cm.getMealKit().getPrice() * cm.getQuantity() * cm.getPortionSize())
                .sum();

        // ایجاد سفارش
        Order order = new Order();
        order.setCustomer(customer);
        order.setAddress(address);
        order.setDeliveryDate(requestDTO.deliveryDate());
        order.setStatus(OrderStatus.PENDING);
        order.setTotalPrice(totalPrice);
        order.setOrderDate(java.time.LocalDateTime.now());

        // تبدیل آیتم‌های سبد خرید به آیتم‌های سفارش
        List<OrderMealKit> orderMealKits = cart.getCartMealKits().stream()
                .map(cm -> new OrderMealKit(
                        order,
                        cm.getMealKit(),
                        cm.getQuantity(),
                        cm.getPortionSize(),
                        cm.getMealKit().getPrice(),
                        new ArrayList<ExcludedIngredient>()
                ))
                .collect(Collectors.toList());
        order.setOrderMealKits(orderMealKits);

        // به‌روزرسانی موجودی
        for (CartMealKit cartMealKit : cart.getCartMealKits()) {
            MealKit mealKit = cartMealKit.getMealKit();
            for (MealKitIngredient mki : mealKit.getMealKitIngredients()) {
                Inventory inventory = mki.getIngredient().getInventory();
                double requiredQuantity = mki.getQuantity() * cartMealKit.getQuantity() * cartMealKit.getPortionSize();
                inventory.setQuantity(inventory.getQuantity() - requiredQuantity);
                inventory.setLastUpdated(java.time.LocalDateTime.now());
                inventoryRepository.save(inventory);
            }
        }

        // ذخیره سفارش
        orderRepository.save(order);
        orderMealKitRepository.saveAll(orderMealKits);

        // تبدیل آیتم‌های سبد خرید به پاسخ
        List<CartMealKitResponseDTO> orderItems = cart.getCartMealKits().stream()
                .map(cartMealKitMapper::toResponseDTO)
                .collect(Collectors.toList());

        // پاک کردن سبد خرید
        cartMealKitRepository.deleteAll(cart.getCartMealKits());
        cart.setCartMealKits(new ArrayList<>());
        cartRepository.save(cart);

        return orderMapper.toResponseDTO(order, orderItems);
    }
}