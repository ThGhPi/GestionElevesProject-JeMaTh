package com.gestioneleves.api.repository.repositoryToken;

import com.gestioneleves.api.entity.entityToken.JwtTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity, Long> {
    Optional<JwtTokenEntity> findByToken(String token);
}

