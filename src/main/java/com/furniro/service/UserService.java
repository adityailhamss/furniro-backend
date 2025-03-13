package com.furniro.service;

import com.furniro.dto.users.CreateUserDTO;
import com.furniro.dto.users.UserDTO;
import com.furniro.entity.User;
import com.furniro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDTO registerUser(CreateUserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User newUser = User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password_hash(passwordEncoder.encode(userDTO.getPasswordHash())) // Hash password
                .build();

        User savedUser = userRepository.save(newUser);
        return new UserDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail(), savedUser.getCreatedAt());
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt()));
    }

    public Optional<UserDTO> getUserByEmail(String email){
        return userRepository.findByEmail(email)
        .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt()));
    }
}
