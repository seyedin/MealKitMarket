package ir.dto.order;

import ir.dto.cartMealKit.CartMealKitResponseDTO;
import ir.entity.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        Long customerId,
        Long addressId,
        LocalDateTime orderDate,
        LocalDateTime deliveryDate,
        OrderStatus status,
        Double totalPrice,
        List<CartMealKitResponseDTO> orderItems
) {
}