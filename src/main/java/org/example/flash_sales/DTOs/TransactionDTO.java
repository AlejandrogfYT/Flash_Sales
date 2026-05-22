package org.example.flash_sales.DTOs;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.example.flash_sales.Enums.Status;
import org.example.flash_sales.Models.SellableItem;
import org.example.flash_sales.Models.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

public record TransactionDTO(

        Long sellableItemId,
        Long amount,
        Instant instant,
        Long userId,
        Status status
) {
}
