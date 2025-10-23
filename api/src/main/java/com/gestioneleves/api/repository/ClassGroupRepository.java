package com.gestioneleves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.ClassGroup;


@Repository
public interface ClassGroupRepository extends CrudRepository<ClassGroup, Long> {
    
    // the classGroups teached by a teacher
    // public List<ClassGroup> findByTeacherId(Long Id);
    
    // the classGroup of a headTeacher
    public Optional<ClassGroup> findByHeadTeacherId(Long Id);

    // the classGroup wher a Student is registered for a given schoolYear
    // public Optional<ClassGroup> findByStudentIdAndSchoolYear(Long id, String schoolYear);
    
    /* Pour le CRUD */
    // Read
    public Optional<ClassGroup> findById(Long id);
    public List<ClassGroup> findAll();

    public ClassGroup save(ClassGroup classGroup); // create and update
    public void deleteById(Long id); // delete
}
