package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.repository.EvaluationRepository;
import com.gestioneleves.api.service.mapper.EvaluationMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository repository;
    private final EvaluationMapper mapper;



    public List<Evaluation> getEvaluations() {
        return repository.findAll();
    }

    public Optional<Evaluation> getEvaluation(Long id) {
        return repository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return repository.save(evaluation);
    }

    public void deleteEvaluation(Long id) {
        repository.deleteById(id);
    }

    public List<EvaluationDTO> getEvaluationByStudentAndTeachingAndPeriod(
        Long studentId, Long teachingId, Date periodStart, Date periodEnd) {
        return repository.findByStudentIdAndTeachingIdAndDateAndTime(studentId, teachingId, periodStart, periodEnd)
            .stream()
            .map(mapper::toDto)
            .toList();
    }
}