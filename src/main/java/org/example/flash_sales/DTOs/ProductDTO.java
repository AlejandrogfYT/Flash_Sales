package org.example.flash_sales.DTOs;

import org.example.flash_sales.Models.Product;
import org.example.flash_sales.Models.User;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        Long stock,
        String userId,
        float offer
) {
    public static ProductDTO fromEntity(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getUser() != null ? product.getUser().getId() : null,
                product.getOffer()
        );
    }

    public Product toEntity(User user) {
        Product product = new Product();
        product.setName(this.name());
        product.setPrice(this.price());
        product.setStock(this.stock());
        product.setUser(user);
        product.setOffer(this.offer());
        return product;
    }
}
