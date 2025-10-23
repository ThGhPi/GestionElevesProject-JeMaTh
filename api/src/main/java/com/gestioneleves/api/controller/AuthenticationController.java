package com.gestioneleves.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.dto.LoginAppUserDto;
import com.gestioneleves.api.dto.RegisterAppUserDto;
import com.gestioneleves.api.dto.response.LoginResponse;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.service.AuthenticationService;
import com.gestioneleves.api.service.JwtService;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> register(@RequestBody RegisterAppUserDto registerAppUserDto) {
        AppUser registeredUser = authenticationService.signup(registerAppUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/profil")
    public ResponseEntity<AppUserDTO> authenticatedUser() {
        AppUserDTO currentUser = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginAppUserDto loginAppUserDto) {
        AppUser authenticatedUser = authenticationService.authenticate(loginAppUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
