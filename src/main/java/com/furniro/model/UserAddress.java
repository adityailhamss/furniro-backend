package com.furniro.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserAddress {
    private Long id;
    private Long userId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String streetAddress;
    private String cityId;
    private Long provinceId;
    private Long countryId;
    private String zipCode;
    private String phone;
    private String email;
    private String additionalInfo;
    private boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}