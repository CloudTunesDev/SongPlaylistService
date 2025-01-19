package com.cloudtunes.songplaylistserv.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

//    @Test
//    void saveUser_ValidInput_ReturnsUserDTO() {
//        // Arrange
//        UserDTO userDTO = createSampleUserDTO();
//        String password = "testPassword";
//        when(userRepository.save(any())).thenReturn(createSampleUser());
//
//        // Act
//        UserDTO result = userService.saveUser(userDTO, password);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(userDTO.getUsername(), result.getUsername());
//        assertEquals(userDTO.getEmail(), result.getEmail());
//        assertEquals(userDTO.getRole(), result.getRole());
//    }

    @Test
    void getUserById_ValidId_ReturnsUserDTO() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(createSampleUser()));

        // Act
        Optional<UserDTO> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(createSampleUser().getUsername(), result.get().getUsername());
        assertEquals(createSampleUser().getEmail(), result.get().getEmail());
        assertEquals(createSampleUser().getRole(), result.get().getRole());
    }

    @Test
    void getUserById_InvalidId_ReturnsEmptyOptional() {
        // Arrange
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<UserDTO> result = userService.getUserById(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

//    @Test
//    void deleteUser_ValidInput_DeletesUser() {
//        // Arrange
//        UserDTO userDTO = createSampleUserDTO();
//
//        // Use lenient() to allow not calling findById during this test
//        lenient().when(userRepository.findById(any())).thenReturn(Optional.of(createSampleUser()));
//
//        // Act
//        userService.deleteUser(userDTO);
//
//        // Assert
//        verify(userRepository, times(1)).delete(any());
//    }


    @Test
    void getAllUsers_ReturnsListOfUserDTOs() {
        // Arrange
        Iterable<User> users = createSampleUsersIterable();
        when(userRepository.findAll()).thenReturn(users);

        // Act
        Iterable<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(2, ((List<UserDTO>) result).size());
    }

    // Helper methods for creating sample data
    private UserDTO createSampleUserDTO() {
        return UserDTO.builder()
                .id(1L)
                .username("TestUser")
                .email("test@example.com")
                .role("USER")
                .build();
    }

    private User createSampleUser() {
        return User.builder()
                .id(1L)
                .username("TestUser")
                .email("test@example.com")
                .role("USER")
                .build();
    }

    private Iterable<User> createSampleUsersIterable() {
        return List.of(createSampleUser(), createSampleUser());
    }
}

