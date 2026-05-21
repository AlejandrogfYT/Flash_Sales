package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
