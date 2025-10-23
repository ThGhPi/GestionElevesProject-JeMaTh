package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService service;

    @GetMapping
    public List<Evaluation> getEvaluations() {
        return service.getEvaluations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluation(@PathVariable Long id) {
        return service.getEvaluation(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evaluation create(@RequestBody Evaluation evaluation) {
        return service.saveEvaluation(evaluation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> update(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        return service.getEvaluation(id)
                .map(existing -> {
                    evaluation.setId(id);
                    return ResponseEntity.ok(service.saveEvaluation(evaluation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getEvaluation(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}