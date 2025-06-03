package ir.mapper;

import ir.dto.CustomerCreateDTO;
import ir.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper{

    public Customer toEntity(
            CustomerCreateDTO customerCreateDTO) {
        return new Customer();
    }
}
