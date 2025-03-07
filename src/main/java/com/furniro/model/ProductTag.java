package com.furniro.model;

import lombok.Data;

@Data
public class ProductTag {
    private Long id;
    private Long productId;
    private Long tagId;
}