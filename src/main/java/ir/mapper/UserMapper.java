package ir.mapper;

import ir.dto.user.UserCreateDTO;
import ir.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(
            UserCreateDTO userCreateDTO) {
        return User.builder()
                .email(userCreateDTO.email())
                .username(userCreateDTO.username())
                .password(userCreateDTO.password())
                .firstName(userCreateDTO.firstName())
                .lastName(userCreateDTO.lastName())
                .phoneNumber(userCreateDTO.phoneNumber())
                .build();
    }
}
