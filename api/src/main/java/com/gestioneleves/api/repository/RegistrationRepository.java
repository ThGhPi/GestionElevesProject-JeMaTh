package com.gestioneleves.api.repository;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.gestioneleves.api.entity.Registration;

@Repository
public interface RegistrationRepository extends ListCrudRepository<Registration, Long> {
    
    // registration linked to a classGroup for a schoolYear
    public List<Registration> findByClassGroupIdAndSchoolYear(Long classGroupId, String schoolYear);
    public List<Registration> findByClassGroupIdOrderBySchoolYear(Long classGroupId);
    
    // registrations of a student in the establishment
    public List<Registration> findByStudentId(Long studentId);

    
    /* Pour le CRUD */
    // Read -> no findById because id is a product key
    public List<Registration> findAllOrderBySchoolYear();

    public List<Registration> saveAll(List<Registration> registrations);
    public Registration save(Registration registration); // create and update
    public void deleteById(Long id); // delete
}
