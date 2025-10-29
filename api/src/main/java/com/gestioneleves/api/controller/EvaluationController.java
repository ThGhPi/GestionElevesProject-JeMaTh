package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService service;

    @GetMapping
    public List<EvaluationDTO> getEvaluations() {
        return service.getEvaluations();
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluation(@PathVariable Long id) {
        return service.getEvaluationById(id);
    }

    @PostMapping
    public EvaluationDTO createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        return service.saveEvaluation(evaluationDTO);
    }

    @PutMapping("/{id}")
    public EvaluationDTO updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO evaluationDTO) {
        evaluationDTO.setId(id);
        return service.saveEvaluation(evaluationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        service.deleteEvaluation(id);
    }

    @GetMapping("/by-student/{studentId}")
    public List<EvaluationDTO> getEvaluationsByStudent(@PathVariable Long studentId) {
        return service.getEvaluationsByStudent(studentId);
    }

    @GetMapping("/by-teaching/{teachingId}")
    public List<EvaluationDTO> getEvaluationsByTeaching(@PathVariable Long teachingId) {
        return service.getEvaluationsByTeaching(teachingId);
    }
}