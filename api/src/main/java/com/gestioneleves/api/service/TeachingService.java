package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.TeachingRepository;
import com.gestioneleves.api.service.mapper.TeachingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TeachingService {
    private final TeachingRepository teachingRepository;
    private final TeachingMapper mapper;

    // GET all (List<TeachingDTO>)
    public List<TeachingDTO> getTeachings() {
        return teachingRepository.findAll()
                .stream()
                .map(mapper::toDto)   // Teaching -> TeachingDTO
                .collect(Collectors.toList());
    }

    // GET one (Optional<TeachingDTO>)
    public TeachingDTO getTeaching(Long id) {
        Teaching teaching = teachingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La matière avec l'id n'existe pas !"));
        return mapper.toDto(teaching);// Teaching -> TeachingDTO
    }

    // CREATE or UPDATE
    public TeachingDTO saveTeaching(TeachingDTO teachingDTO) {
        // DTO -> Entity
        Teaching entity = mapper.toEntity(teachingDTO);
        // persist
        Teaching saved = teachingRepository.save(entity);
        // Entity -> DTO
        return mapper.toDto(saved);
    }

    // DELETE
    public void deleteTeaching(Long id) {
        teachingRepository.deleteById(id);
    }

    // Toutes les matières d'un prof
        public List<TeachingDTO> getTeachingByTeacher(Long teacherId) {
        return teachingRepository.findByTeacherId(teacherId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        }

    //Matière par classe
    public List<TeachingDTO> getTeachingsByClassGroup(Long classGroupId) {
        return teachingRepository.findByClassGroupId(classGroupId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

   // Nom des matières
    public List<TeachingDTO> getTeachingsBySubjectName(String subjectName) {
        return teachingRepository.findBySubjectName(subjectName)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    // Renvoie une seule matière si elle existe, pour un groupe donné et un nom de matière donné.
    public TeachingDTO getTeachingByClassGroupAndSubject(Long classGroupId, String subjectName) {
        Teaching teaching = teachingRepository
                .findByClassGroupIdAndSubjectName(classGroupId, subjectName)
                .orElseThrow(() -> new RuntimeException(
                        "Aucune matière '" + subjectName + "' trouvée pour la classe " + classGroupId
                ));
        return mapper.toDto(teaching);
    }
}