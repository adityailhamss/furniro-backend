package com.furniro.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "address_id")
    private UserAddress address;
    
    private BigDecimal total_amount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;
    
    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;
}