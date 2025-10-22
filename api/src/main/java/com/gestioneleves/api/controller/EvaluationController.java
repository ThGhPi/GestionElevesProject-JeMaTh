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
    public List<Evaluation> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Evaluation create(@RequestBody Evaluation evaluation) {
        return service.save(evaluation);
    }

    @PutMapping
    public ResponseEntity<Evaluation> update(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        return service.findById(id)
                .map(existing -> {
                    evaluation.setId(id);
                    return ResponseEntity.ok(service.save(evaluation));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}