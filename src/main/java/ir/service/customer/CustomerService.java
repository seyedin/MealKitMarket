package ir.service.customer;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.User;

public interface CustomerService {

    CustomerResponseDTO createWithUser(CustomerCreateDTO dto, User user);
}
