// package com.furniro.service.impl;

// import com.furniro.dto.ProductDTO;
// import com.furniro.entity.Product;
// import com.furniro.repository.ProductRepository;
// import com.furniro.service.ProductService;
// import org.springframework.stereotype.Service;

// import java.util.Arrays;
// import java.util.List;
// import java.util.stream.Collectors;

// @Service
// public class ProductServiceImpl implements ProductService {
//     private final ProductRepository productRepository;

//     public ProductServiceImpl(ProductRepository productRepository) {
//         this.productRepository = productRepository;
//     }

//     @Override
//     public List<ProductDTO> getAllProducts() {
//         return productRepository.findAllWithTagsAndReviews().stream()
//                 .map(this::convertToProductDTO)
//                 .collect(Collectors.toList());
//     }

//     private ProductDTO convertToProductDTO(Object[] result) {
//         Product product = (Product) result[0];
//         String tagNames = (String) result[1];
//         Double avgRating = (Double) result[2];
//         Long reviewCount = (Long) result[3];

//         ProductDTO dto = new ProductDTO();
//         dto.setId(product.getId());
//         dto.setName(product.getName());
//         dto.setDescription(product.getDescription());
//         dto.setMoreDescription(product.getMoreDescription());
//         dto.setPrice(product.getPrice());
//         dto.setOriginalPrice(product.getOriginalPrice());
//         dto.setImageUrl(product.getImageUrl());
        
//         // Convert comma-separated tags to List
//         dto.setTags(tagNames != null ? 
//             Arrays.asList(tagNames.split(",")) : 
//             List.of());
        
//         // Set review data
//         dto.setReview(avgRating != null ? avgRating : 0.0);
//         dto.setCountReview(reviewCount != null ? reviewCount.intValue() : 0);

//         return dto;
//     }

//     // ... other methods remain unchanged ...
// }