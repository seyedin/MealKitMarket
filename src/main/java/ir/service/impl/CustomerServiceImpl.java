package ir.service.impl;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Customer;
import ir.entity.User;
import ir.mapper.CustomerMapper;
import ir.repository.CustomerRepository;
import ir.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public CustomerResponseDTO createWithUser(CustomerCreateDTO dto, User user) {
        Customer customer = customerMapper.toEntity(dto);
        customer.setUser(user);
        return customerMapper.toResponseDTO(
                customerRepository.save(customer));
    }
}
