package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Registration;

@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long> {

}
