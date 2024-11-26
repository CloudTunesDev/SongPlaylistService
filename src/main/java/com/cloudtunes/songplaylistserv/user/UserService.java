package com.cloudtunes.songplaylistserv.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
    }

    public UserDTO saveUser(UserDTO userDTO, String password) {
        //String encodedPassword = passwordEncoder.encode(password);

        User newUser = User.builder()
                .username(userDTO.getUsername())
                //.password(encodedPassword)
                .email(userDTO.getEmail())
                .role(userDTO.getRole() != null ? userDTO.getRole() : UserRole.GUEST.toString())
                .build();

        User savedUser = userRepository.save(newUser);
        return UserConverter.ToUserDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserConverter::ToUserDTO);
    }


    public void deleteUser(UserDTO userDTO) {
        User user = UserConverter.ToEntity(userDTO);
        userRepository.delete(user);
    }

    public Iterable<UserDTO> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), false)
                .map(UserConverter::ToUserDTO)
                .collect(Collectors.toList());
    }

    private boolean matchesPassword (String password, String encodedPassword) {
        //return passwordEncoder.matches(password, encodedPassword);
        return true;
    }
}
