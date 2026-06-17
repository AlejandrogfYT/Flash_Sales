package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
