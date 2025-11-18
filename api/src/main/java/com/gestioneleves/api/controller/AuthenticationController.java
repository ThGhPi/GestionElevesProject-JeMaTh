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
import com.gestioneleves.api.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<AppUserDTO> newPassword(@RequestBody List<LoginAppUserDTO> appUserDtoList) {
        AppUserDTO currentUser = authenticationService.changePassword(appUserDtoList);

        return ResponseEntity.ok(currentUser);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginAppUserDTO loginAppUserDto, HttpServletResponse response) {
        System.out.println("Controller hit! User: " + loginAppUserDto.getUsername());
        String jwtToken = authenticationService.login(loginAppUserDto);

        ResponseCookie cookie = ResponseCookie.from("jwt", jwtToken)
                .httpOnly(true)
                // .secure(true)
                // .sameSite("None")
                // for dev :
                .secure(false)
                .sameSite("None")
                .path("/")
                .maxAge(3600) // 1 heure
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of("message", "Logged in"));
    }
}
