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

//    @PostMapping()
//    public ResponseEntity<UserDTO> addUser() {
//        return ResponseEntity.ok(userService.saveUser(createUserRequest.getUser(), createUserRequest.getPassword()));
//    }

    @RolesAllowed("ADMIN")
    @DeleteMapping()
    public ResponseEntity deleteUser(@RequestBody UserDTO user) {
        userService.deleteUser(user);
        return ResponseEntity.ok().build();
    }
}
