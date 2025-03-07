package com.furniro.model;

import lombok.Data;

@Data
public class Province {
    private Long id;
    private Long countryId;
    private String name;
    private String code;
}