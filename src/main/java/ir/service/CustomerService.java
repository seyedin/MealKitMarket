package ir.service;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.dto.user.UserResponseDTO;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerService {

    CustomerResponseDTO signup(CustomerCreateDTO createDTO);
}
