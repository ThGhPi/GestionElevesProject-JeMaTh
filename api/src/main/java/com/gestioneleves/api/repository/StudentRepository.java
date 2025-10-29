package com.gestioneleves.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    
    List<Student> findByLegalGuardiansId(Long id); // find list of children related to a guardian
    //List<Student> findByClassGroupIdAndSchoolYear(Long id, String schoolYear); // find list of student of a classGroup & namedQuery
    //List<Student> findByTeachingId(Iterable<Long> ids); // find list of teachers by list of teaching ids
    


     
    
    
}
