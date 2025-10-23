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

    public List<Evaluation> getEvaluations() {
        return evaluations.findAll();
    }
    public Optional<Evaluation> getEvaluation(Long id) {
        return evaluations.findById(id);
    }
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluations.save(evaluation);
    }
    public void deleteEvaluation(Long id) {
        evaluations.deleteById(id);
    }
}