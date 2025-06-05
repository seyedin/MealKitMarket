package ir.service.impl;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.dto.user.UserResponseDTO;
import ir.entity.Customer;
import ir.entity.Role;
import ir.entity.User;
import ir.exception.RoleNotFoundException;
import ir.mapper.CustomerMapper;
import ir.repository.CustomerRepository;
import ir.service.CustomerService;
import ir.service.RoleService;
import ir.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final CustomerMapper customerMapper;
    private final RoleService roleService;
    private Role roleCustomer;

    @PostConstruct
    public void init() {
        this.roleCustomer = roleService.findByName("ROLE_CUSTOMER")
                .orElseThrow(()-> new RoleNotFoundException("Customer"));
    }

    @Transactional
    @Override
    public CustomerResponseDTO signup(CustomerCreateDTO createDTO) {
        User user = userService.save(createDTO.userDto());
        user.getRoles().add(roleCustomer);
        Customer customer = customerMapper.toEntity(createDTO);
        customer.setUser(user);
        return customerMapper.toResponseDTO(
                customerRepository.save(customer));
    }
}
