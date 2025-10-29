package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.repository.EvaluationRepository;
import com.gestioneleves.api.service.mapper.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationService {
    private final EvaluationRepository repository;
    private final EvaluationMapper mapper;

    public List<EvaluationDTO> getEvaluations() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluationById(Long id) {
        Evaluation evaluation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("l'évaluation n'existe pas !"));
        return mapper.toDto(evaluation);
    }

    public EvaluationDTO saveEvaluation(EvaluationDTO evaluationDTO) {
        Evaluation entity = mapper.toEntity(evaluationDTO);
        Evaluation saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public void deleteEvaluation(Long id) {
        repository.deleteById(id);
    }

    public List<EvaluationDTO> getEvaluationsByStudent(Long studentId) {
        return repository.findByStudentId(studentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluationDTO> getEvaluationsByTeaching(Long teachingId) {
        return repository.findByTeachingId(teachingId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluationDTO> getEvaluationByStudentAndTeachingAndPeriod(
            Long studentId, Long teachingId, Date periodStart, Date periodEnd) {
        return repository.findByStudentIdAndTeachingIdAndDateAndTime(studentId, teachingId, periodStart, periodEnd)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}