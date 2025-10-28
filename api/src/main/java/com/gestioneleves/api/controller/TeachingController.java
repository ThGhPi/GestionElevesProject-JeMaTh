package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.TeachingDTO;
import com.gestioneleves.api.entity.Teaching;
import com.gestioneleves.api.service.TeachingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachings")
@RequiredArgsConstructor
public class TeachingController {

    private TeachingService teachingService;

    @GetMapping
    public List<TeachingDTO> getTeachings() {
        return teachingService.getTeachings();
    }

    @GetMapping("/{id}")
    public TeachingDTO getTeaching(@PathVariable Long id) {
        return teachingService.getTeaching(id);
    }

    @PostMapping
    public TeachingDTO createTeaching(@RequestBody TeachingDTO teachingDTO) {
        return teachingService.saveTeaching(teachingDTO);
    }

    @PutMapping("/{id}")
    public TeachingDTO updateTeaching(@PathVariable Long id, @RequestBody TeachingDTO teachingDTO) {
        // on force l'id pour être sûr de mettre à jour la bonne matière
        teachingDTO.setId(id);
        return teachingService.saveTeaching(teachingDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTeaching(@PathVariable Long id) {
        teachingService.deleteTeaching(id);
    }

    @GetMapping("/by-teacher/{teacherId}")
    public List<TeachingDTO> getTeachingByTeacher(@PathVariable Long teacherId) {
        return teachingService.getTeachingByTeacher(teacherId);
    }

    @GetMapping("/by-class/{classGroupId}")
    public List<TeachingDTO> getTeachingsByClassGroup(@PathVariable Long classGroupId) {
        return teachingService.getTeachingsByClassGroup(classGroupId);
    }

    @GetMapping("/by-subject/{subjectName}")
    public List<TeachingDTO> getTeachingsBySubjectName(@PathVariable String subjectName) {
        return teachingService.getTeachingsBySubjectName(subjectName);
    }

    @GetMapping("/by-class/{classGroupId}/subject/{subjectName}")
    public TeachingDTO getTeachingByClassGroupAndSubject(@PathVariable Long classGroupId,
                                                         @PathVariable String subjectName) {
        return teachingService.getTeachingByClassGroupAndSubject(classGroupId, subjectName);
    }
}