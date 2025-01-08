package com.cloudtunes.songplaylistserv.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    void createUser() {
        User user = new User("john_doe", "password123", "john.doe@example.com", "USER");
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
    }

    @Test
    void createUserWithBuilder() {
        User user = User.builder()
                .username("jane_doe")
                .password("securePass")
                .email("jane.doe@example.com")
                .role("ADMIN")
                .build();

        assertNotNull(user);
        assertEquals("jane_doe", user.getUsername());
        assertEquals("securePass", user.getPassword());
        assertEquals("jane.doe@example.com", user.getEmail());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        User user1 = new User("john_doe", "password123", "john.doe@example.com", "USER");
        User user2 = new User("john_doe", "password123", "john.doe@example.com", "USER");
        User user3 = new User("jane_doe", "securePass", "jane.doe@example.com", "ADMIN");

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }
    @Test
    void testNoArgsConstructor() {
        User user = new User();
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }

    @Test
    void testAllArgsConstructor() {
        User user = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
    }

    @Test
    void testBuilderWithAllArgsConstructor() {
        User user = User.builder()
                .id(1L)
                .username("john_doe")
                .password("password123")
                .email("john.doe@example.com")
                .role("USER")
                .build();

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
    }

    @Test
    void testToString() {
        User user = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");
        String expectedToString = "User(id=1, username=john_doe, password=password123, email=john.doe@example.com, role=USER)";
        assertEquals(expectedToString, user.toString());
    }

    @Test
    void testEqualsAndHashCodeWithLombok() {
        User user1 = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");
        User user2 = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");
        User user3 = new User(2L, "jane_doe", "securePass", "jane.doe@example.com", "ADMIN");

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testDataAnnotation() {
        User user1 = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");
        User user2 = new User(1L, "john_doe", "password123", "john.doe@example.com", "USER");

        // toString
        assertEquals(user1.toString(), user2.toString());

        // equals
        assertTrue(user1.equals(user2));
        assertTrue(user2.equals(user1));

        // hashCode
        assertEquals(user1.hashCode(), user2.hashCode());

        // Getter and Setter
        user1.setId(2L);
        assertEquals(2L, user1.getId());

        user1.setUsername("jane_doe");
        assertEquals("jane_doe", user1.getUsername());

        user1.setPassword("newPassword");
        assertEquals("newPassword", user1.getPassword());

        user1.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", user1.getEmail());

        user1.setRole("ADMIN");
        assertEquals("ADMIN", user1.getRole());
    }
}



