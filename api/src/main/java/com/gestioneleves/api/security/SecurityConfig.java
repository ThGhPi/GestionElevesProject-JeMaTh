package com.gestioneleves.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gestioneleves.api.service.usersDetailsService.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
 

    private final CustomUserDetailsService custom;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/login").permitAll()
            .requestMatchers("/api/users/create/**").hasRole("ADMIN")
            .requestMatchers("/api/users/**").hasAnyRole("ADMIN","TEACHER")
            //.requestMatchers("/api/users/create").permitAll()
            .anyRequest()
            .authenticated()
        )
        .userDetailsService(custom)
        .httpBasic(Customizer.withDefaults()); 

        return http.build();
    } 

}


