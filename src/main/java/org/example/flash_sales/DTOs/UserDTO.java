package org.example.flash_sales.DTOs;

import org.example.flash_sales.Enums.UserType;
import org.example.flash_sales.Models.User;

public record UserDTO(
        Long id,
        String username,
        String email,
        String password,
        UserType type
) {
    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getType()
        );
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username());
        user.setEmail(this.email());
        user.setPassword(this.password());
        user.setType(this.type());
        return user;
    }
}
