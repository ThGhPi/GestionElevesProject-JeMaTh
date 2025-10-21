package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Teaching;

@Repository
public interface TeachingRepository extends CrudRepository<Teaching, Long> {

}
