package com.furniro.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PaymentMethod {
    private Long id;
    private String name;
    private String code;
    private boolean isActive;
    private LocalDateTime createdAt;
}