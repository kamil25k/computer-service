package pl.kamil25k.pcservice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<User> getAllUsers1() {
        return userRepository.findAll();
    }

    public Optional<UserDto> getUserById(long id) {
        return userRepository.findById(id).map(userMapper::mapToDto);
    }

    public void updateUser(UserDto user){
        userRepository.save(userMapper.mapToEntity(user));
    }

    public void saveUser(UserDto user) {
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userMapper.mapToEntity(user));
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }


}
