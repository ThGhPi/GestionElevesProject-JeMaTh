package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registrations")
public class RegistrationController {

    @Autowired
    private RegistrationService service;

    // Liste inscription triée par année scolaire
    @GetMapping
    public List<Registration> getRegistrations() {
        return service.getRegistrationsOrderedBySchoolYear();
    }

    // Par classe + année
    @GetMapping("/by-class/{classGroupId}/year/{schoolYear}")
    public List<Registration> byClassAndYear(@PathVariable Long classGroupId, @PathVariable String schoolYear) {
        return service.getByClassGroupIdAndSchoolYear(classGroupId, schoolYear);
    }

    // Par classe (toutes années, triées)
    @GetMapping("/by-class/{classGroupId}")
    public List<Registration> byClass(@PathVariable Long classGroupId) {
        return service.getByClassGroupIdOrderByYear(classGroupId);
    }

    // Par élève
    @GetMapping("/by-student/{studentId}")
    public List<Registration> byStudent(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    // Création simple
    @PostMapping
    public Registration create(@RequestBody Registration registration) {
        return service.saveRegistration(registration);
    }

    // Création en lot
    @PostMapping("/save-all")
    public List<Registration> saveAll(@RequestBody List<Registration> list) {
        return service.saveAllRegistrations(list);
    }

    // Mise à jour par id (pas de check findById car non exposé dans le repo)
    @PutMapping("/{id}")
    public ResponseEntity<Registration> update(@PathVariable Long id, @RequestBody Registration registration) {
        registration.setId(id);
        return ResponseEntity.ok(service.saveRegistration(registration));
    }

    // Suppression par id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }
}
