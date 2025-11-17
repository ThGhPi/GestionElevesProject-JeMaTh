package com.gestioneleves.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.dto.LoginAppUserDTO;
import com.gestioneleves.api.dto.RegisterAppUserDTO;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.service.AuthenticationService;
import com.gestioneleves.api.service.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AppUserDTO> register(@RequestBody RegisterAppUserDTO registerAppUserDto) {
        AppUserDTO registeredUser = authenticationService.signup(registerAppUserDto);

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
    public ResponseEntity<?> authenticate(@RequestBody LoginAppUserDTO loginAppUserDto) {
        String jwtToken = authenticationService.login(loginAppUserDto);

        ResponseCookie cookie = ResponseCookie.from("jwt", jwtToken)
            .httpOnly(true)
            .secure(true)
            .path("/")
            .maxAge(60 * 60) // 1 heure
            .sameSite("Strict")
            .build();

    return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, cookie.toString())
            .body(Map.of("message", "Logged in"));
    }
}
