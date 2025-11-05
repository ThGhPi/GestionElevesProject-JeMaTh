package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.EvaluationDTO;
import com.gestioneleves.api.entity.Evaluation;
import com.gestioneleves.api.entity.Student;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.repository.EvaluationRepository;
import com.gestioneleves.api.repository.StudentRepository;
import com.gestioneleves.api.repository.TeachingRepository;
import com.gestioneleves.api.service.mapper.EvaluationMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository repository;
    private final EvaluationMapper mapper;
    private final TeachingRepository teachingRepository;
    private final StudentRepository studentRepository;

   

    public List<EvaluationDTO> getEvaluations() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EvaluationDTO getEvaluation(Long id) {
        Evaluation eval = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pas d'évaluation"));
        return mapper.toDto(eval);
    }

    public EvaluationDTO saveOrUpdateEvaluation(Long id, EvaluationDTO evaldto) {
        Evaluation eval = (id != null && repository.existsById(id))
                    ? repository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("evaluation introuvable avec cette Id: " +""+ id))
                    : new Evaluation();

                    eval.setNote(evaldto.getNote());
                    eval.setWeight(evaldto.getWeight());
                    eval.setDateAndTime(evaldto.getDateAndTime());

        if (evaldto.getTeachingDTO() != null) {
        Teaching teaching = teachingRepository.findById(evaldto.getTeachingDTO().getId())
                .orElseThrow(() -> new NoSuchElementException("Cours introuvable"));
        eval.setTeaching(teaching);
        }

        if (evaldto.getStudentDTO() != null) {
            Student stud = studentRepository.findById(evaldto.getStudentDTO().getId())
            .orElseThrow(() -> new NoSuchElementException("Eleve non trouvé avec cet Id"));
        eval.setStudent(stud);    
        }
        
        Evaluation evaluationsaved = repository.save(eval);
        return mapper.toDto(evaluationsaved);
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

    public List<EvaluationDTO> getEvaluationsByStudentAndTeachingAndPeriod(
            Long studentId, Long teachingId, LocalDate periodStart, LocalDate periodEnd) {
        LocalDateTime start = periodStart.atStartOfDay();
        LocalDateTime end = periodEnd.atTime(LocalTime.MAX);
        return repository.findByStudentIdAndTeachingIdAndDateAndTime(studentId, teachingId, start, end)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}