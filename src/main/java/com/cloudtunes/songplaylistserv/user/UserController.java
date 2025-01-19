package com.cloudtunes.songplaylistserv.user;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<Iterable<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserById(id).get());
    }


    @RolesAllowed("ADMIN")
    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody UserDTO user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }

    // GDPR: Access User Data
    @GetMapping("/userdata")
    public ResponseEntity<UserDTO> getMyData() {
        // Assuming the user ID is retrieved from the security context
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(userService.getUserById(userId).get());
    }

    // GDPR: Rectify User Data
    @PutMapping("/userdata")
    public ResponseEntity<UserDTO> updateMyData(@RequestHeader("Authorization") String token, @RequestBody UserDTO userDTO) {
        Long userId = getCurrentUserIdFromToken(token);
        userDTO.setId(userId);

        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    private Long getCurrentUserIdFromToken(String token) {
        return 1L; // Placeholder
    }

    // GDPR: Delete User Data
    @DeleteMapping("/userdata")
    public ResponseEntity<Void> deleteMyData() {
        // Assuming the user ID is retrieved from the security context
        Long userId = getCurrentUserId();
        userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

    private Long getCurrentUserId() {
        // Implement logic to retrieve the current user's ID from the security context
        return 1L; // Placeholder
    }
}
