package org.example.flash_sales.DTOs;

public record RegisterRequest(
        String username,
        String email,
        String password,
        String firstName,
        String lastName
) {
}
