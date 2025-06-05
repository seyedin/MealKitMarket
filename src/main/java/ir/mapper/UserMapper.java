package ir.mapper;

import ir.dto.user.UserCreateDTO;
import ir.dto.user.UserResponseDTO;
import ir.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

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
                .roles(new HashSet<>())
                .build();
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPhoneNumber()
        );
    }
}
