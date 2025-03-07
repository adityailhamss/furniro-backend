package com.furniro.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_addresses")
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String first_name;
    private String last_name;
    private String company_name;
    private String street_address;

    
    @Column(name = "city_id")
    private Long cityId;
    
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    
    private String zip_code;
    private String phone;
    private String email;
    private String additional_info;
    private boolean is_default;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}