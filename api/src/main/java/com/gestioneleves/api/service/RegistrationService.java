package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.RegistrationDTO;
import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.Registration;
import com.gestioneleves.api.entity.RegistrationPK;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.RegistrationRepository;
import com.gestioneleves.api.service.mapper.RegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper mapper;

    public List<RegistrationDTO> getRegistrations() {
        return registrationRepository.findAllByOrderBySchoolYearAsc()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public RegistrationDTO getRegistration(RegistrationPK id) {
        Registration registration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription introuvable."));
        return mapper.toDto(registration);
    }

    /* --------- CREATE / UPDATE --------- */
    public RegistrationDTO saveRegistration(RegistrationDTO dto) {
        Registration entity = mapper.toEntity(dto);
        Registration saved = registrationRepository.save(entity);
        return mapper.toDto(saved);
    }

    /* --------- DELETE --------- */
    public void deleteRegistration(Long studentId, Long classGroupId) {
        RegistrationPK id = new RegistrationPK(studentId, classGroupId);
        registrationRepository.deleteById(id);
    }

    public List<RegistrationDTO> getRegistrationsByStudent(Long studentId) {
        return registrationRepository.findByStudentId(studentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<RegistrationDTO> getRegistrationsByClass(Long classGroupId) {
        return registrationRepository.findByClassGroupIdOrderBySchoolYear(classGroupId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


    public List<RegistrationDTO> getRegistrationsByClassAndYear(Long classGroupId, String schoolYear) {
        return registrationRepository.findByClassGroupIdAndSchoolYear(classGroupId, schoolYear)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}