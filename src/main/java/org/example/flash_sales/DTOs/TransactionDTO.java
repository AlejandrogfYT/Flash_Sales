package org.example.flash_sales.DTOs;

import org.example.flash_sales.Enums.Status;
import org.example.flash_sales.Models.SellableItem;
import org.example.flash_sales.Models.Transaction;
import org.example.flash_sales.Models.User;

import java.time.Instant;

public record TransactionDTO(
        Long id,
        Long sellableItemId,
        Long amount,
        Instant instant,
        String userId,
        Status status
) {
    public static TransactionDTO fromEntity(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return new TransactionDTO(
                transaction.getId(),
                transaction.getSellableItem() != null ? transaction.getSellableItem().getId() : null,
                transaction.getAmount(),
                transaction.getInstant(),
                transaction.getUser() != null ? transaction.getUser().getId() : null,
                transaction.getStatus()
        );
    }

    public Transaction toEntity(User user, SellableItem sellableItem) {
        Transaction transaction = new Transaction();
        transaction.setSellableItem(sellableItem);
        transaction.setAmount(this.amount());
        transaction.setUser(user);
        transaction.setStatus(this.status() != null ? this.status() : Status.PENDING);
        return transaction;
    }
}
