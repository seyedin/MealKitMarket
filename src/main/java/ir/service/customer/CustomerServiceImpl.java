package ir.service.customer;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Customer;
import ir.entity.User;
import ir.mapper.CustomerMapper;
import ir.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    @Override
    public Customer createWithUser(CustomerCreateDTO dto, User user) {
        Customer customer = customerMapper.toEntity(dto);
        customer.setUser(user);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findByUserId(Long userId) {
        return customerRepository.findByUser_Id(userId);
    }
}
