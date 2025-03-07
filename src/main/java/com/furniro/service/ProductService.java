package com.furniro.service;

import com.furniro.dto.CreateProductDTO;
import com.furniro.dto.ProductDTO;
import com.furniro.entity.Product;
import com.furniro.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.time.ZoneId;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllWithTagsAndReviews().stream()
                .map(this::convertToProductDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Object[] result = productRepository.findByIdWithTagsAndReviews(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductDTO(result);
    }

    public ProductDTO createProduct(CreateProductDTO createProductDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(createProductDTO, product);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        Object[] result = productRepository.findByIdWithTagsAndReviews(savedProduct.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductDTO(result);
    }

    public ProductDTO updateProduct(Long id, CreateProductDTO updateProductDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        BeanUtils.copyProperties(updateProductDTO, product);
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        Object[] result = productRepository.findByIdWithTagsAndReviews(savedProduct.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToProductDTO(result);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToProductDTO(Object[] result) {
        ProductDTO dto = new ProductDTO();
        dto.setId(((Number) result[0]).longValue());
        dto.setName((String) result[1]);
        dto.setDescription((String) result[2]);
        dto.setMoreDescription((String) result[3]);
        dto.setStock((Integer) result[4]);
        dto.setPrice((BigDecimal) result[5]);
        dto.setOriginalPrice((BigDecimal) result[6]);
        dto.setImageUrl((String) result[7]);

        // Parse formatted datetime strings
        String createdAtStr = (String) result[8];
        String updatedAtStr = (String) result[9];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dto.setCreatedAt(createdAtStr != null ? LocalDateTime.parse(createdAtStr, formatter) : null);
        dto.setUpdatedAt(updatedAtStr != null ? LocalDateTime.parse(updatedAtStr, formatter) : null);

        // Handle tags
        String tagNames = (String) result[10];
        dto.setTags(tagNames != null ?
                Arrays.asList(tagNames.split(",")) :
                List.of());

        // Handle review data
        BigDecimal avgRating = (BigDecimal) result[11];
        dto.setReview(avgRating != null ? avgRating.doubleValue() : 0.0);
        dto.setCountReview(((Number) result[12]).intValue());

        return dto;
    }
}