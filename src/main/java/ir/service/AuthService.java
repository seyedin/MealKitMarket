package ir.service;

import ir.dto.auth.LoginRequestDTO;
import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;

public interface AuthService {

    CustomerResponseDTO signupCustomer(CustomerCreateDTO dto);

    String loginAndGenerateToken(LoginRequestDTO loginRequest);
}
