package com.gestioneleves.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.dto.LoginAppUserDTO;
import com.gestioneleves.api.dto.RegisterAppUserDTO;
import com.gestioneleves.api.dto.response.LoginResponse;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.service.AuthenticationService;
import com.gestioneleves.api.service.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtService jwtService;    
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody RegisterAppUserDTO registerAppUserDto) {
        AppUser registeredUser = authenticationService.signup(registerAppUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @GetMapping("/profil")
    public ResponseEntity<AppUserDTO> authenticatedUser() {
        AppUserDTO currentUser = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/profil")
    public ResponseEntity<AppUserDTO> newPassword(@RequestBody List<LoginAppUserDTO> appUserDtoList ) {
        AppUserDTO currentUser = authenticationService.changePassword(appUserDtoList);
        
        return ResponseEntity.ok(currentUser);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginAppUserDTO loginAppUserDto) {
        AppUser authenticatedUser = authenticationService.authenticate(loginAppUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
