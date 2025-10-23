package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.ClassGroup;
import com.gestioneleves.api.service.ClassGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-groups")
public class ClassGroupController {

    @Autowired
    private ClassGroupService service;

    @GetMapping
    public List<ClassGroup> getClassGroups() {
        return service.getClassGroups();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassGroup> getClassGroup(@PathVariable Long id) {
        return service.getClassGroup(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClassGroup create(@RequestBody ClassGroup classGroup) {
        return service.saveClassGroup(classGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassGroup> update(@PathVariable Long id, @RequestBody ClassGroup classGroup) {
        return service.getClassGroup(id)
                .map(existing -> {
                    classGroup.setId(id);
                    return ResponseEntity.ok(service.saveClassGroup(classGroup));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.getClassGroup(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteClassGroup(id);
        return ResponseEntity.noContent().build();
    }
}