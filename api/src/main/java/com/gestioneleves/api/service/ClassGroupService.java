package com.gestioneleves.api.service;


import com.gestioneleves.api.dto.ClassGroupDTO;
import com.gestioneleves.api.entity.ClassGroup;
import com.gestioneleves.api.repository.ClassGroupRepository;
import com.gestioneleves.api.service.mapper.ClassGroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClassGroupService {

    private final ClassGroupRepository repository;
    private final ClassGroupMapper mapper;



    public List<ClassGroupDTO> getClassGroups() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ClassGroupDTO getClassGroup(Long id) {
        ClassGroup classGroup = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucune classe trouvé !"));
        return mapper.toDto(classGroup);
    }

    public ClassGroupDTO saveClassGroup(ClassGroupDTO classGroupDTO) {
        ClassGroup entity = mapper.toEntity(classGroupDTO);
        ClassGroup saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public void deleteClassGroup(Long id) {
        repository.deleteById(id);
    }

    public ClassGroupDTO getClassGroupByHeadTeacher(Long headTeacherId) {
        ClassGroup classGroup = repository.findByHeadTeacher_Id(headTeacherId)
                .orElseThrow(() -> new RuntimeException("Aucune classe trouvée pour le prof"));
        return mapper.toDto(classGroup);
    }

    public ClassGroupDTO getClassGroupByName(String name) {
        ClassGroup classGroup = repository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Aucune classe trouvée avec ce nom"));
        return mapper.toDto(classGroup);
    }
}