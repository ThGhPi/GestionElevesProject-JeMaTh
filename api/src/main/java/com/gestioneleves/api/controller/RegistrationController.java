package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.ClassGroupDTO;
import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.entity.RegistrationPK;
import com.gestioneleves.api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
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
    public List<RegistrationDTO> getRegistrationsByClassAndYear(
            @PathVariable Long classGroupId,
            @PathVariable String schoolYear) {
        return service.getRegistrationsByClassAndYear(classGroupId, schoolYear);
    }

    /* ----------------------- NOUVELLES METHODES ----------------------- */

    //  Par étudiant + année
    @GetMapping("/by-student/{studentId}/year/{schoolYear}")
    public List<RegistrationDTO> getRegistrationsByStudentAndYear(
            @PathVariable Long studentId,
            @PathVariable String schoolYear) {
        return service.getRegistrationsByStudentAndYear(studentId, schoolYear);
    }

    //  Une inscription précise student + class
    @GetMapping("/{studentId}/{classGroupId}")
    public RegistrationDTO getByStudentAndClass(
            @PathVariable Long studentId,
            @PathVariable Long classGroupId) {
        return service.getRegistrationByStudentAndClass(studentId, classGroupId);
    }

    //  classGroupId actuel de l'étudiant
    @GetMapping("/student/{studentId}/class-group")
    public Long getClassGroupIdByStudent(@PathVariable Long studentId) {
        return service.getClassGroupByStudent(studentId);
    }

    //  ClassGroupDTO actuel de l'étudiant
    @GetMapping("/student/{studentId}/class-group/full")
    public ClassGroupDTO getClassGroupDTOByStudent(@PathVariable Long studentId) {
        return service.getClassGroupDTOByStudent(studentId);
    }
}
