package com.gestioneleves.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Student;

@Repository
public interface StudentRepository extends ListCrudRepository<Student, Long> {
    
    
    public List<Student> findByLegalGuardiansId(Long id); // find list of children related to a guardian
    // public List<Student> findByClassGroupIdAndSchoolYear(Long id, String schoolYear); // find list of student of a classGroup & namedQuery
    // public List<Student> findByTeachingId(Iterable<Long> ids); // find list of teachers by list of teaching ids
    
    /* Pour le CRUD */
    // Read
    public Optional<Student> findById(Long id);
    public List<Student> findAll();

    public Student save(Student student); // create and update
    public void deleteById(Long id); // delete
}
