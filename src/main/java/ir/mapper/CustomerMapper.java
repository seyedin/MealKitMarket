package ir.mapper;

import ir.dto.customer.CustomerCreateDTO;
import ir.dto.customer.CustomerResponseDTO;
import ir.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper{

    private final UserMapper userMapper;

    public Customer toEntity(
            CustomerCreateDTO customerCreateDTO) {
        return new Customer();
    }

    public CustomerResponseDTO toResponseDTO(
            Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                userMapper.toResponseDTO(customer.getUser())
        );
    }
}
