package com.cloudtunes.songplaylistserv.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserConverterTests {

    @Test
    public void testToUserDTO() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setPassword("password123");
        user.setEmail("test@example.com");
        user.setRole("USER");

        // Act
        UserDTO userDTO = UserConverter.ToUserDTO(user);

        // Assert
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getRole(), userDTO.getRole());
    }

    @Test
    public void testToUserDTOWithNullUser() {
        // Act
        UserDTO userDTO = UserConverter.ToUserDTO(null);

        // Assert
        assertNull(userDTO);
    }

    @Test
    public void testToEntity() {
        // Arrange
        UserDTO userDTO = UserDTO.builder()
                .id(1L)
                .username("testUser")
                .password("password123")
                .email("test@example.com")
                .role("USER")
                .build();

        // Act
        User user = UserConverter.ToEntity(userDTO);

        // Assert
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getRole(), user.getRole());
    }

    @Test
    public void testToEntityWithNullUserDTO() {
        // Act
        User user = UserConverter.ToEntity(null);

        // Assert
        assertNull(user);
    }
}

