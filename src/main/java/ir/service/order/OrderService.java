package ir.service.order;

import ir.dto.order.OrderCreateDTO;
import ir.dto.order.OrderResponseDTO;
import ir.entity.User;

public interface OrderService {
    OrderResponseDTO createOrderFromCart(OrderCreateDTO requestDTO, User user);
}