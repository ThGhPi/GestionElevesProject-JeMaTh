package com.gestioneleves.api.controller;

import com.gestioneleves.api.dto.ClassGroupDTO;
import com.gestioneleves.api.service.ClassGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-groups")
@RequiredArgsConstructor
public class ClassGroupController {

    private final ClassGroupService service;

    @GetMapping
    public List<ClassGroupDTO> getClassGroups() {
        return classGroupService.getClassGroups();
    }

    @GetMapping("/{id}")
    public ClassGroupDTO getClassGroup(@PathVariable Long id) {
        return classGroupService.getClassGroup(id);
    }

    @PostMapping
    public ClassGroupDTO create(@RequestBody ClassGroupDTO classGroupDTO) {
        return classGroupService.saveClassGroup(classGroupDTO);
    }

    @PutMapping("/{id}")
    public ClassGroupDTO updateClassGroup(@PathVariable Long id, @RequestBody ClassGroupDTO classGroupDTO) {
        classGroupDTO.setId(id);
        return classGroupService.saveClassGroup(classGroupDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClassGroup(@PathVariable Long id) {
        classGroupService.deleteClassGroup(id);
    }

    @GetMapping("/by-head-teacher/{headTeacherId}")
    public ClassGroupDTO getClassGroupByHeadTeacher(@PathVariable Long headTeacherId) {
        return classGroupService.getClassGroupByHeadTeacher(headTeacherId);
    }
}