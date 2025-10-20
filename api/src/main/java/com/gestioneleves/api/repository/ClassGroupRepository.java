package com.gestioneleves.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.ClassGroup;

@Repository
public interface ClassGroupRepository extends CrudRepository<ClassGroup, Integer> {
}
