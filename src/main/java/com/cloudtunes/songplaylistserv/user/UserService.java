package com.cloudtunes.songplaylistserv.user;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;
    private final RabbitTemplate rabbitTemplate;

    public UserService(UserRepository userRepository, RabbitTemplate rabbitTemplate) {
        this.userRepository = userRepository;
        //this.passwordEncoder = passwordEncoder;
        this.rabbitTemplate = rabbitTemplate;
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
        UserDTO savedUserDTO = UserConverter.ToUserDTO(savedUser);
        rabbitTemplate.convertAndSend("userQueue", savedUserDTO);
        return UserConverter.ToUserDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserConverter::ToUserDTO);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        rabbitTemplate.convertAndSend("userQueue", "Deleted user with ID " + id);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user = userRepository.save(user);
        UserDTO updatedUserDTO = convertToDTO(user);
        rabbitTemplate.convertAndSend("userQueue", updatedUserDTO);
        return updatedUserDTO;
    }

    public void deleteUser(UserDTO userDTO) {
        User user = UserConverter.ToEntity(userDTO);
        userRepository.delete(user);
        rabbitTemplate.convertAndSend("userQueue", userDTO);
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

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    private User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();
    }
}
