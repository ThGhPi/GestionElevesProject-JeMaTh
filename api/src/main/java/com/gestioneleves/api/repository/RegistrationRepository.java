package com.gestioneleves.api.repository;

import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.entity.RegistrationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, RegistrationId> {}
