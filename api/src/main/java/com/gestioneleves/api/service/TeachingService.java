package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.AppUserRepository;
import com.gestioneleves.api.repository.ClassGroupRepository;
import com.gestioneleves.api.repository.TeachingRepository;
import com.gestioneleves.api.service.mapper.TeachingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeachingService {
    private final TeachingRepository repository;
    private final TeachingMapper mapper;
    private final AppUserRepository appUserRepository;
    private final ClassGroupRepository classGroupRepository;


    public List<TeachingDTO> getTeachings() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)   // Teaching -> TeachingDTO
                .collect(Collectors.toList());
    }


    public TeachingDTO getTeaching(Long id) {
        Teaching teaching = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("La matière avec l'id n'existe pas !"));
        return mapper.toDto(teaching);// Teaching -> TeachingDTO
    }

    // CREATE or UPDATE
    public TeachingDTO saveTeaching(TeachingDTO teachingDTO) {
        // DTO -> Entity
        Teaching entity = mapper.toEntity(teachingDTO, classGroupRepository, appUserRepository);
        // persist
        Teaching saved = repository.save(entity);
        // Entity -> DTO
        return mapper.toDto(saved);
    }

    // DELETE
    public void deleteTeaching(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                "There should be no student linked (by evaluations or school reports) to this teaching to delete it :",
                e.getCause());
        }
    }

    // Toutes les matières d'un prof
    public List<TeachingDTO> getTeachingByTeacher(String name) {
        return repository.searchTeacherByName(name)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        }

    //Matière par classe
    public List<TeachingDTO> getTeachingsByClassGroup(Long classGroupId) {
        return repository.findByClassGroupId(classGroupId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

   // Nom des matières
    public List<TeachingDTO> getTeachingsBySubjectName(String subjectName) {
        return repository.findBySubjectName(subjectName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // Renvoie une seule matière si elle existe, pour un groupe donné et un nom de matière donné.
    public TeachingDTO getTeachingByClassGroupAndSubject(Long classGroupId, String subjectName) {
        Teaching teaching = repository
                .findByClassGroupIdAndSubjectName(classGroupId, subjectName)
                .orElseThrow(() -> new RuntimeException(
                        "Aucune matière '" + subjectName + "' trouvée pour la classe " + classGroupId
                ));
        return mapper.toDto(teaching);
    }
}