package com.furniro.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String moreDescription;
    private Integer stock;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String imageUrl;
    private List<String> tags;
    private Double review;
    private Integer countReview;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}