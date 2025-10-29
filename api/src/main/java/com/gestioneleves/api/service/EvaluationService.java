package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.repository.EvaluationRepository;
import com.gestioneleves.api.service.mapper.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationMapper mapper;

    public List<EvaluationDTO> getEvaluations() {
        return evaluationRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluationById(Long id) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("l'évaluation n'existe pas !"));
        return mapper.toDto(evaluation);
    }

    public EvaluationDTO saveEvaluation(EvaluationDTO evaluationDTO) {
        Evaluation entity = mapper.toEntity(evaluationDTO);
        Evaluation saved = evaluationRepository.save(entity);
        return mapper.toDto(saved);
    }

    public void deleteEvaluation(Long id) {
        evaluationRepository.deleteById(id);
    }

    public List<EvaluationDTO> getEvaluationsByStudent(Long studentId) {
        return evaluationRepository.findByStudentId(studentId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EvaluationDTO> getEvaluationsByTeaching(Long teachingId) {
        return evaluationRepository.findByTeachingId(teachingId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}