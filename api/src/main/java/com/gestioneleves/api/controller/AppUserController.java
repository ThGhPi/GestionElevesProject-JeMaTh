package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService service;

    @GetMapping
    public List<AppUserDTO> getAllUsers() {//on retourne une liste d'appUserDto
        return service.findAll();// en faisant appelle a la methode findAll() de appUserService
    }

    @GetMapping("/{id}")
    public AppUserDTO getUserById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUserDTO createUser(@RequestBody AppUserDTO dto) {
        return service.create(dto);
    }

}
