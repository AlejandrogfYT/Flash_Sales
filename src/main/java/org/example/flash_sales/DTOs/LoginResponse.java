package org.example.flash_sales.DTOs;

public record LoginResponse(
        String access_token,
        String refresh_token,
        Long expires_in
) {
}
