package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.StudentDTO;
import com.gestioneleves.api.entity.Student;
import com.gestioneleves.api.repository.StudentRepository;
import com.gestioneleves.api.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public List<StudentDTO> getStudents() {
        return studentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("l'élève n\'existe pas !"));
        return mapper.toDto(student);
    }

    public StudentDTO saveStudent(StudentDTO studentDTO) {
        Student student = mapper.toEntity(studentDTO);
        Student saved =  studentRepository.save(student);
        return mapper.toDto(saved);
    }

    public List<StudentDTO> getByLegalGuardian(Long id) {
        return studentRepository.findByLegalGuardiansId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}