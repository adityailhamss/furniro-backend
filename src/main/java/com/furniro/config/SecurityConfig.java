package com.furniro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nonaktifkan CSRF jika perlu
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/products/**").permitAll() // Public endpoint
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Basic Authentication

        return http.build();
    }
}
