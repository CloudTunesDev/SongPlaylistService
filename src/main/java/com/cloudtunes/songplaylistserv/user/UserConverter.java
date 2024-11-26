package com.cloudtunes.songplaylistserv.user;

public class UserConverter {

    public static UserDTO ToUserDTO(User user) {
        if (user == null)
            return null;

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public static User ToEntity(UserDTO userDTO) {
        if (userDTO == null)
            return null;

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }
}
