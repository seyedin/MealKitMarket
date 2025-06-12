package ir.service.auth;

import ir.dto.auth.LoginRequestDTO;
import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    CustomerResponseDTO signupCustomer(CustomerCreateDTO dto);

    String loginAndGenerateToken(LoginRequestDTO loginRequest);

    void logout(HttpServletRequest request);
}
