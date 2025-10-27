package com.gestioneleves.api.service;


import com.gestioneleves.api.service.serviceToken.JwtService;
import com.gestioneleves.api.dto.AppUserDTOCreateAdmin;
import com.gestioneleves.api.dto.AppUserDTOLogin;
import com.gestioneleves.api.dto.AppUserDTOResAdm;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.repository.AppUserRepository;
import com.gestioneleves.api.service.mapper.AppUserMapper;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserService {
    
    private final AppUserRepository userRepository;
    private final AppUserMapper mapper;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AppUserDTOResAdm create(AppUserDTOCreateAdmin dto){
        
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("l'email existe déjà");
        }
            AppUser u = mapper.toEntity(dto);
            u.setPassword(encoder.encode(dto.getPassword()));
            AppUser saved = userRepository.save(u);
        return mapper.toResAdm(saved);
    } /**
        dto.setPassword(encoder.encode(dto.getPassword()))
        return mapper.toResAdm(userRepository.save(mapper.toEntity(dto)))
    */
 
    public List<AppUserDTOResAdm> read() {

        List<AppUser> users =  userRepository.findAll();
        if (users.isEmpty()) {
            throw new NoSuchElementException("Il n'y a aucun élément dans la base");   
        }
        List<AppUserDTOResAdm>  uresps = new ArrayList<>();
        for (AppUser u : users) {uresps.add(mapper.toResAdm(u));}
        return uresps; 
    }/**
    encore plus rapide 
    return users.stream()
                .map(mapper::toResAdm)
                .toList
    */

    public Map<String, String> login(AppUserDTOLogin dto) {
    return userRepository.findByUsername(dto.getUsername())
        .filter(user -> encoder.matches(dto.getPassword(), user.getPassword()))
        .map(user -> Map.of(
            "username", user.getUsername(),
            "role", user.getRole().name(),
            "token", jwtService.generateToken(user.getUsername(), user.getRole().name())
        ))
        .orElseThrow(() -> new RuntimeException("Identifiants incorrects"));
    } 
}
  