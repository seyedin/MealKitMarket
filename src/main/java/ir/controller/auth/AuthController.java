package ir.controller.auth;

import ir.dto.auth.JwtResponseDTO;
import ir.dto.auth.LoginRequestDTO;
import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup/customer")
    public ResponseEntity<CustomerResponseDTO> signupCustomer(
            @RequestBody @Valid CustomerCreateDTO createDTO
            ){
        return ResponseEntity.ok(
                authService.signupCustomer(createDTO)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        String token = authService.loginAndGenerateToken(loginRequest);
        return ResponseEntity.ok(
                new JwtResponseDTO(token)
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        log.info("Logout request triggered");
        authService.logout(request);
        return ResponseEntity.ok(
                "Successfully logged out"
        );
    }
}
