package com.gestioneleves.api.service;

import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.repository.EvaluationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {
    private EvaluationRepository evaluations;

    public EvaluationService(EvaluationRepository evaluations) {
        this.evaluations = evaluations;
    }

    public List<Evaluation> findAll() {
        return evaluations.findAll();
    }
    public Optional<Evaluation> findById(Long id) {
        return evaluations.findById(id);
    }
    public Evaluation save(Evaluation evaluation) {
        return evaluations.save(evaluation);
    }
    public void deleteById(Long id) {
        evaluations.deleteById(id);
    }
}