package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.AppUserDTOLogin;
import com.gestioneleves.api.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final AppUserService Service;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AppUserDTOLogin loginDTO) {
        return ResponseEntity.ok(Service.login(loginDTO));
    }
}

