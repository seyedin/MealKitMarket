package ir.service;

import ir.dto.user.UserCreateDTO;
import ir.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save (UserCreateDTO dto);
}
