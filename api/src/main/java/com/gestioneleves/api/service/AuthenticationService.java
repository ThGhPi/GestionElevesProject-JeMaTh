package com.gestioneleves.api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestioneleves.api.dto.LoginAppUserDto;
import com.gestioneleves.api.dto.RegisterAppUserDto;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AppUser signup(RegisterAppUserDto input) {
        AppUser user = new AppUser();
        user.setUsername(input.getUsername());
        user.setLastname(input.getLastname());
        user.setFirstname(input.getFirstname());
        user.setPhoneNumber(input.getPhoneNumber());
        user.setPostalAddress(input.getPostalAddress());
        user.setRole(input.getRole());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public AppUser authenticate(LoginAppUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
