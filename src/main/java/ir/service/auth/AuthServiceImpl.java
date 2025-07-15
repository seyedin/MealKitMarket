package ir.service.auth;

import ir.dto.auth.LoginRequestDTO;
import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Customer;
import ir.entity.Role;
import ir.entity.User;
import ir.exception.RoleNotFoundException;
import ir.mapper.CustomerMapper;
import ir.security.JwtTokenProvider;
import ir.service.cart.CartService;
import ir.service.customer.CustomerService;
import ir.service.role.RoleService;
import ir.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final RoleService roleService;
    private final CustomerMapper customerMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenBlacklistService tokenBlacklistService;

//    @PostConstruct
//    public void init() {
//        this.roleCustomer = roleService.findByName("ROLE_CUSTOMER")
//                .orElseThrow(()-> new RoleNotFoundException("Customer"));
//    }

    @Transactional
    @Override
    public CustomerResponseDTO signupCustomer(CustomerCreateDTO dto) {
        Role roleCustomer = roleService.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RoleNotFoundException("Customer"));
        User user = userService.save(dto.userDto());
        user.getRoles().add(roleCustomer);
        Customer customer = customerService.createWithUser(dto, user);
        cartService.createCart(customer);
        return customerMapper.toResponseDTO(customer);
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

    @Override
    public void logout(HttpServletRequest request) {
        String token = jwtTokenProvider.extractTokenFromRequest(request);
        if (token != null) {
            tokenBlacklistService.blacklistToken(token);
        }
        SecurityContextHolder.clearContext();
    }

}
