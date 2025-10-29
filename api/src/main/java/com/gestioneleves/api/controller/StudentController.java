package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.StudentDTO;
import com.gestioneleves.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping
    public List<StudentDTO> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @GetMapping("/by-guardian/{guardianId}")
    public List<StudentDTO> getStudentsByGuardian(@PathVariable("guardianId") Long guardianId) {
        return service.getByLegalGuardian(guardianId);
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return service.saveStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        // On force l'id dans le DTO pour être sûr qu'on met à jour le bon élève
        studentDTO.setId(id);
        return service.saveStudent(studentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}