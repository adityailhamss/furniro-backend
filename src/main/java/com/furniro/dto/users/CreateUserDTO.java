package com.furniro.dto.users;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateUserDTO {
    private String username;
    private String email;
    private String passwordHash;
    private LocalDateTime createdAt;
}
