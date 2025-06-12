package ir.service.user;

import ir.dto.user.UserCreateDTO;
import ir.entity.User;
import ir.exception.BusinessDuplicateException;
import ir.mapper.UserMapper;
import ir.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User save(UserCreateDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        checkUniques(user);
        return userRepository.save(user);
    }

    private void checkUniques(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new BusinessDuplicateException("username", user.getUsername());
        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new BusinessDuplicateException("email", user.getEmail());
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent())
            throw new BusinessDuplicateException("phoneNumber", user.getPhoneNumber());
    }
}
