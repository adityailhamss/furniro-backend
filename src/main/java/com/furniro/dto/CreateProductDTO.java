package com.furniro.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateProductDTO {
    private String name;
    private String description;
    private String moreDescription;
    private Integer stock;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String imageUrl;
}