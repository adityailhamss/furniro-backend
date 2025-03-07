package com.furniro.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class OrderStatus {
    private Long id;
    private String name;
    private String code;
    private String description;
    private LocalDateTime createdAt;
}