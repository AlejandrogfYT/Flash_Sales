package org.example.flash_sales.DTOs;

import org.example.flash_sales.Enums.UserType;
import org.example.flash_sales.Models.User;

public record UserDTO(
        String id,
        String username,
        String email,
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
                user.getType()
        );
    }

    public User toEntity() {
        User user = new User();
        user.setUsername(this.username());
        user.setEmail(this.email());
        user.setType(this.type());
        return user;
    }
}
