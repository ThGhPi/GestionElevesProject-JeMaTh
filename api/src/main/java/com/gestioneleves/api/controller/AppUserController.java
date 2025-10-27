package com.gestioneleves.api.controller;


import com.gestioneleves.api.dto.AppUserDTOCreateAdmin;
import com.gestioneleves.api.dto.AppUserDTOResAdm;
import com.gestioneleves.api.service.AppUserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;






@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService service;


    @PostMapping("/users/create")
    public ResponseEntity<AppUserDTOResAdm> create(@RequestBody AppUserDTOCreateAdmin dto){
        AppUserDTOResAdm createdAdmin = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTOResAdm>> read() {
           List<AppUserDTOResAdm> users = service.read(); 
        return ResponseEntity
            .ok(users);
    }
}
