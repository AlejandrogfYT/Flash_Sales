package org.example.flash_sales.DTOs;

import org.example.flash_sales.Enums.UserType;

public record UserDTO(
        String username,
        String email,
        UserType type
) {

}
