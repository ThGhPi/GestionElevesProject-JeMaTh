package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.entity.RegistrationPK;
import com.gestioneleves.api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService service;

    @GetMapping
    public List<RegistrationDTO> getRegistrations() {
        return service.getRegistrations();
    }

    @GetMapping("/student/{studentId}/class/{classGroupId}")
    public RegistrationDTO getRegistration(@PathVariable Long studentId, @PathVariable Long classGroupId) {
        RegistrationPK id = new RegistrationPK(studentId, classGroupId);
        return service.getRegistration(id);
    }

    /* --------- CREATE --------- */
    @PostMapping
    public RegistrationDTO createRegistration(@RequestBody RegistrationDTO dto) {
        return service.saveRegistration(dto);
    }

    /* --------- UPDATE --------- */
    @PutMapping
    public RegistrationDTO updateRegistration(@RequestBody RegistrationDTO dto) {
        return service.saveRegistration(dto);
    }

    /* --------- DELETE --------- */
    @DeleteMapping("/student/{studentId}/class/{classGroupId}")
    public void deleteRegistration(@PathVariable Long studentId, @PathVariable Long classGroupId) {
        service.deleteRegistration(studentId, classGroupId);
    }

    @GetMapping("/by-student/{studentId}")
    public List<RegistrationDTO> getRegistrationsByStudent(@PathVariable Long studentId) {
        return service.getRegistrationsByStudent(studentId);
    }

    @GetMapping("/by-class/{classGroupId}")
    public List<RegistrationDTO> getRegistrationsByClass(@PathVariable Long classGroupId) {
        return service.getRegistrationsByClass(classGroupId);
    }

    @GetMapping("/by-class/{classGroupId}/year/{schoolYear}")
    public List<RegistrationDTO> getRegistrationsByClassAndYear(@PathVariable Long classGroupId,
                                                                @PathVariable String schoolYear) {
        return service.getRegistrationsByClassAndYear(classGroupId, schoolYear);
    }

}
