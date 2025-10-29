package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.EvaluationDTO;
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
    public List<EvaluationDTO> getEvaluations() {
        return service.getEvaluations();
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluation(@PathVariable Long id) {
        return service.getEvaluation(id);
    }

    @PostMapping
    public EvaluationDTO create(Long id,@RequestBody EvaluationDTO evaluationdto) {
        return service.saveOrUpdateEvaluation(id,evaluationdto);
    }

    @PutMapping("/{id}")
    public EvaluationDTO update(@PathVariable Long id, @RequestBody EvaluationDTO evaluationDTO) {
        return service.saveOrUpdateEvaluation(id, evaluationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }
}