package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.Student;
import com.gestioneleves.api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository students;

    public StudentService(StudentRepository students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students.findAll();
    }
    public Optional<Student> getStudent(Long id) {
        return students.findById(id);
    }

    public Student saveStudent(Student student) {
        return students.save(student);
    }

    public void deleteStudent(Long id) {
        students.deleteById(id);
    }
}