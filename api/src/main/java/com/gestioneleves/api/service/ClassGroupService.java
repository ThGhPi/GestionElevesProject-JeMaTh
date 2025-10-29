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
    private final ClassGroupRepository classGroupRepository;
    private final ClassGroupMapper mapper;

    public List<ClassGroupDTO> getClassGroups() {
        return classGroupRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ClassGroupDTO getClassGroup(Long id) {
        ClassGroup classGroup = classGroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aucune classe trouvé !"));
        return mapper.toDto(classGroup);
    }

    public ClassGroupDTO saveClassGroup(ClassGroupDTO classGroupDTO) {
        ClassGroup entity = mapper.toEntity(classGroupDTO);
        ClassGroup saved = classGroupRepository.save(entity);
        return mapper.toDto(saved);
    }

    public void deleteClassGroup(Long id) {
        classGroupRepository.deleteById(id);
    }

    public ClassGroupDTO getClassGroupByHeadTeacher(Long headTeacherId) {
        ClassGroup classGroup = classGroupRepository.findByHeadTeacherId(headTeacherId)
                .orElseThrow(() -> new RuntimeException("Aucune classe trouvée pour le prof"));
        return mapper.toDto(classGroup);
    }
}