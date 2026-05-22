package org.example.flash_sales.DTOs;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.flash_sales.Models.User;

import java.math.BigDecimal;

public record ProductDTO(
        String name,
        BigDecimal price,
        Long stock,
        Long userId,
        float offer

) {
}
