package ir.controller.order;

import ir.dto.order.OrderCreateDTO;
import ir.dto.order.OrderResponseDTO;
import ir.entity.User;
import ir.service.order.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-from-cart")
    public ResponseEntity<OrderResponseDTO> createOrderFromCart(
            @Valid @RequestBody OrderCreateDTO requestDTO,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(orderService.createOrderFromCart(requestDTO, user));
    }
}