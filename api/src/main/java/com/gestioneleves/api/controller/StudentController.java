package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.StudentDTO;
import com.gestioneleves.api.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
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
    public StudentDTO getStudent(Long id) { 
        return service.getStudent(id);
    }     

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO create(@RequestBody StudentDTO studentDTO, @PathVariable Long id) {
        return service.saveOrUpdate(id, studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO update(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        return service.saveOrUpdate(id, studentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}