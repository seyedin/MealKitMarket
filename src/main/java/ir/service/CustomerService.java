package ir.service;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.dto.user.UserResponseDTO;
import ir.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerService {

    CustomerResponseDTO createWithUser(CustomerCreateDTO dto, User user);
}
