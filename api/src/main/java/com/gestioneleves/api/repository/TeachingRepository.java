package com.gestioneleves.api.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Teaching;

@Repository
public interface TeachingRepository extends ListCrudRepository<Teaching, Long> {

}
