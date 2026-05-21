package org.example.flash_sales.Repositories;

import org.example.flash_sales.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<Product, Long> {
}
