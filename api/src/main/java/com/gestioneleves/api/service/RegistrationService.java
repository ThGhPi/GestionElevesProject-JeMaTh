package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.entity.RegistrationPK;
import com.gestioneleves.api.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    private final RegistrationRepository registrations;

    public RegistrationService(RegistrationRepository registrations) {
        this.registrations = registrations;
    }

    public List<Registration> getRegistrationsOrderedBySchoolYear() {
        return registrations.findAllByOrderBySchoolYearAsc();
    }

    public List<Registration> getByClassGroupIdAndSchoolYear(Long classGroupId, String schoolYear) {
        return registrations.findByClassGroupIdAndSchoolYear(classGroupId, schoolYear);
    }

    public List<Registration>  getByClassGroupIdOrderByYear(Long classGroupId) {
        return registrations.findByClassGroupIdOrderBySchoolYear(classGroupId);
    }

    public List<Registration> getByStudentId(Long studentId) {
        return registrations.findByStudentId(studentId);
    }

    public Registration saveRegistration(Registration registration) {
        return registrations.save(registration);
    }

    public List<Registration> saveAllRegistrations(List<Registration> listRegistrations) {
        return registrations.saveAll(listRegistrations);
    }

    public void deleteRegistration(RegistrationPK id) {
        registrations.deleteById(id);
    }
}