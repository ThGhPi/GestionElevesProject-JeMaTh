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

    private EvaluationService evaluationService;

    @GetMapping
    public List<EvaluationDTO> getEvaluations() {
        return evaluationService.getEvaluations();
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluation(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @PostMapping
    public EvaluationDTO createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        return evaluationService.saveEvaluation(evaluationDTO);
    }

    @PutMapping("/{id}")
    public EvaluationDTO updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO evaluationDTO) {
        evaluationDTO.setId(id);
        return evaluationService.saveEvaluation(evaluationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
    }

    @GetMapping("/by-student/{studentId}")
    public List<EvaluationDTO> getEvaluationsByStudent(@PathVariable Long studentId) {
        return evaluationService.getEvaluationsByStudent(studentId);
    }

    @GetMapping("/by-teaching/{teachingId}")
    public List<EvaluationDTO> getEvaluationsByTeaching(@PathVariable Long teachingId) {
        return evaluationService.getEvaluationsByTeaching(teachingId);
    }
}