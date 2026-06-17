package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.TransactionDTO;
import org.example.flash_sales.Enums.Status;
import org.example.flash_sales.Models.SellableItem;
import org.example.flash_sales.Models.Transaction;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.SellableItemRepository;
import org.example.flash_sales.Repositories.TransactionRepository;
import org.example.flash_sales.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final SellableItemRepository sellableItemRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, SellableItemRepository sellableItemRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.sellableItemRepository = sellableItemRepository;
    }

    @Transactional
    public TransactionDTO createTransaction(TransactionDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        SellableItem item = sellableItemRepository.findById(dto.sellableItemId()).orElseThrow();

        if (item.getStock() < dto.amount()) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        item.setStock(item.getStock() - dto.amount());
        sellableItemRepository.save(item);

        Transaction transaction = dto.toEntity(user, item);
        Transaction saved = transactionRepository.save(transaction);
        return TransactionDTO.fromEntity(saved);
    }

    public TransactionDTO getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        return TransactionDTO.fromEntity(transaction);
    }

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionDTO::fromEntity)
                .toList();
    }

    @Transactional
    public TransactionDTO updateTransaction(Long id, TransactionDTO dto) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow();
        transaction.setStatus(dto.status());
        Transaction saved = transactionRepository.save(transaction);
        return TransactionDTO.fromEntity(saved);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
