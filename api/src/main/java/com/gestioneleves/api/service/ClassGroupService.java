package com.gestioneleves.api.service;


import com.gestioneleves.api.entity.ClassGroup;
import com.gestioneleves.api.repository.ClassGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassGroupService {
    private final ClassGroupRepository classGroups;

    public ClassGroupService(ClassGroupRepository classGroups) {
        this.classGroups = classGroups;
    }

    public List<ClassGroup> getClassGroups() {
        return classGroups.findAll();
    }
    public Optional<ClassGroup> getClassGroup(Long id) {
        return classGroups.findById(id);
    }

    public ClassGroup saveClassGroup(ClassGroup classGroup) {
        return classGroups.save(classGroup);
    }

    public void deleteClassGroup(Long id) {
        classGroups.deleteById(id);
    }
}