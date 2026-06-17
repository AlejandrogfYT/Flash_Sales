package org.example.flash_sales.Services;

import org.example.flash_sales.DTOs.ProductDTO;
import org.example.flash_sales.Models.Product;
import org.example.flash_sales.Models.User;
import org.example.flash_sales.Repositories.ProductRepository;
import org.example.flash_sales.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public ProductDTO createProduct(ProductDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow();
        Product product = dto.toEntity(user);
        Product saved = productRepository.save(product);
        return ProductDTO.fromEntity(saved);
    }

    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductDTO.fromEntity(product);
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDTO::fromEntity)
                .toList();
    }

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow();
        User user = userRepository.findById(dto.userId()).orElseThrow();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setUser(user);
        product.setOffer(dto.offer());
        Product saved = productRepository.save(product);
        return ProductDTO.fromEntity(saved);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
