package ir.service.impl;

import ir.dto.auth.LoginRequestDTO;
import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Role;
import ir.entity.User;
import ir.exception.RoleNotFoundException;
import ir.security.JwtTokenProvider;
import ir.service.AuthService;
import ir.service.CustomerService;
import ir.service.RoleService;
import ir.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CustomerService customerService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private Role roleCustomer;


    @PostConstruct
    public void init() {
        this.roleCustomer = roleService.findByName("ROLE_CUSTOMER")
                .orElseThrow(()-> new RoleNotFoundException("Customer"));
    }

    @Transactional
    @Override
    public CustomerResponseDTO signupCustomer(CustomerCreateDTO dto) {
        User user = userService.save(dto.userDto());
        user.getRoles().add(roleCustomer);
        return customerService.createWithUser(dto, user);
    }

    @Override
    public String loginAndGenerateToken(LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(loginRequest.username());
    }
}
