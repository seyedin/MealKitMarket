package ir.mapper;

import ir.dto.cartMealKit.CartMealKitResponseDTO;
import ir.dto.order.OrderResponseDTO;
import ir.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CartMealKitMapper cartMealKitMapper;

    public OrderResponseDTO toResponseDTO(Order order, List<CartMealKitResponseDTO> orderItems) {
        return new OrderResponseDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getAddress().getId(),
                order.getOrderDate(),
                order.getDeliveryDate(),
                order.getStatus(),
                order.getTotalPrice(),
                orderItems
        );
    }
}