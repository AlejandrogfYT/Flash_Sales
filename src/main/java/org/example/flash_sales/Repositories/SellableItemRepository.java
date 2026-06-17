package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.SellableItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellableItemRepository extends JpaRepository<SellableItem, Long> {
}
