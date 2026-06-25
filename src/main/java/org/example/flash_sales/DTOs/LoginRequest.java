package org.example.flash_sales.DTOs;

public record LoginRequest(
        String username,
        String password
) {
}
