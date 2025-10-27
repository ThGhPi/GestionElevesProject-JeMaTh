package com.gestioneleves.api.service.serviceToken;

import com.gestioneleves.api.entity.entityToken.JwtTokenEntity;
import com.gestioneleves.api.repository.repositoryToken.JwtTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtTokenRepository tokenRepository;

   private static final String SECRET = System.getenv("JWT_SECRET") != null
        ? System.getenv("JWT_SECRET")
        : "clef_par_defaut_a_remplacer_32_octets_minimum";

private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());


    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1h

    public String generateToken(String username, String role) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiry))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();

        // pour la base de donnée (la sauvegarde)
        JwtTokenEntity tok = JwtTokenEntity.builder()
                .token(token)
                .username(username)
                .issuedAt(now)
                .expiresAt(expiry)
                .revoked(false)
                .expired(false)
                .build();

        tokenRepository.save(tok);

        return token;
    }

    //je crée ma méthode qui me dit si le temps du token est toujours valide
    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isExpired() && !t.isRevoked() && t.getExpiresAt().isAfter(Instant.now()))
                .isPresent();
    }

    public void revokeToken(String token) {
        tokenRepository.findByToken(token).ifPresent(t -> {
            t.setRevoked(true);
            tokenRepository.save(t);
        });
    }
}

