package ir.service.customer;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Customer;
import ir.entity.User;

import java.util.Optional;

public interface CustomerService {

    Customer createWithUser(CustomerCreateDTO dto, User user);

    Optional<Customer> findByUserId(Long userId);
}
